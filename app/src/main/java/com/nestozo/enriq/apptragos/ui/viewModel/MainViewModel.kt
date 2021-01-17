package com.nestozo.enriq.apptragos.ui.viewModel

import androidx.lifecycle.*
import com.nestozo.enriq.apptragos.domain.Repo
import com.nestozo.enriq.apptragos.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {

    private val drinksData = MutableLiveData<String>()

    init{
        setDrink("margarita")
    }

    fun setDrink(drinkName: String){
        drinksData.value = drinkName
    }

    val fetchDrinkList = drinksData.distinctUntilChanged().switchMap { drinkName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getDrinkList(drinkName))
            }catch (e: Exception){
                //emit(Resource.Failure(e))
            }
        }
    }
}