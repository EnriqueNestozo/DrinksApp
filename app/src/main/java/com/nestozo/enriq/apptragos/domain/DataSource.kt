package com.nestozo.enriq.apptragos.domain

import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkEntity
import com.nestozo.enriq.apptragos.vo.Resource
import com.nestozo.enriq.apptragos.vo.RetrofitClient

interface DataSource {
    suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>>

    suspend fun insertDrinkIntoRoom(drink: DrinkEntity)

    suspend fun getFavoritesDrinks(): Resource<List<DrinkEntity>>

    suspend fun deleteDrink(drink: DrinkEntity)
}