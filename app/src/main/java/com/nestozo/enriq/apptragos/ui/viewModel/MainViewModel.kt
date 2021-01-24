package com.nestozo.enriq.apptragos.ui.viewModel

import androidx.lifecycle.*
import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkEntity
import com.nestozo.enriq.apptragos.domain.Repo
import com.nestozo.enriq.apptragos.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo) : ViewModel() {

    private val drinksData = MutableLiveData<String>()

    init {
        setDrink("margarita")
    }

    fun setDrink(drinkName: String) {
        drinksData.value = drinkName
    }

    val fetchDrinkList = drinksData.distinctUntilChanged().switchMap { drinkName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getDrinkList(drinkName))
            } catch (e: Exception) {
                //emit(Resource.Failure(e))
            }
        }
    }

    fun saveDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            repo.insertDrink(drink)
        }
    }

    fun getFavoritesDrinks() =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getFavoriteDrinks())
            } catch (e: Exception) {
                //emit(Resource.Failure(e))
            }
        }

    fun deleteDrink(drink: Drink) {
        viewModelScope.launch {
            repo.deleteDrink(DrinkEntity(drink.drinkId,drink.imagen,drink.nombre,drink.descripcion,drink.hasAlcohol))
            //toastHelper.sendToast("Cocktail deleted from favorites")
        }
    }
}