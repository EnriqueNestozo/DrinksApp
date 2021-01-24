package com.nestozo.enriq.apptragos.data

import com.nestozo.enriq.apptragos.AppDataBase
import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkEntity
import com.nestozo.enriq.apptragos.domain.DataSource
import com.nestozo.enriq.apptragos.vo.Resource
import com.nestozo.enriq.apptragos.vo.RetrofitClient

class DataSourceImpl(private val appDatabase: AppDataBase): DataSource  {

    override suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getDrinkByName(drinkName).drinkList)
    }

    override suspend fun insertDrinkIntoRoom(drink: DrinkEntity){
        appDatabase.drinkDao().insertFavorite(drink)
    }

    override suspend fun getFavoritesDrinks(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.drinkDao().getAllFavoritesDrinks())
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        appDatabase.drinkDao().deleteDrink(drink)
    }

    /*
    private val generateDrinkList = listOf<Drink>(
        Drink("https://i.pinimg.com/originals/d0/ae/e0/d0aee0140841f7d359154ecc4415319c.jpg","margarita","Con azucar, vodka y nueces"),
        Drink("https://cdn.kiwilimon.com/recetaimagen/28863/th5-320x320-30611.jpg","Sunrise","Bebida rica"),
        Drink("https://cdn.clubsolaris.com/com/ES/es/blog/bebidas-en-los-hoteles-solaris-1/bebida-miami-vice-en-hoteles-solaris.jpg", "Toro", "Toro con brandy"),
        Drink("https://cdn7.kiwilimon.com/recetaimagen/36265/400x300/44761.jpg.webp", "Shot", "Tequila puro"),
        Drink("https://i.pinimg.com/originals/d0/ae/e0/d0aee0140841f7d359154ecc4415319c.jpg","Vampiro","Con azucar, vodka y nueces"),
        Drink("https://cdn.kiwilimon.com/recetaimagen/28863/th5-320x320-30611.jpg","Mojito","Bebida rica"),
        Drink("https://cdn.clubsolaris.com/com/ES/es/blog/bebidas-en-los-hoteles-solaris-1/bebida-miami-vice-en-hoteles-solaris.jpg", "Mojito sucio", "Toro con brandy"),
        Drink("https://cdn7.kiwilimon.com/recetaimagen/36265/400x300/44761.jpg.webp", "Pitufo", "Tequila puro"),
        Drink("https://cdn.clubsolaris.com/com/ES/es/blog/bebidas-en-los-hoteles-solaris-1/bebida-miami-vice-en-hoteles-solaris.jpg", "Ron picante", "Toro con brandy"),
        Drink("https://cdn7.kiwilimon.com/recetaimagen/36265/400x300/44761.jpg.webp", "Michelada", "Tequila puro")
    )

    fun getDrinkList(): Resource<List<Drink>>{
        return Resource.Success(generateDrinkList)
    }
    */
}