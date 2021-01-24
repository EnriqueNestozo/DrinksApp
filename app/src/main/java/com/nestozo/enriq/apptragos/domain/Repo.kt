package com.nestozo.enriq.apptragos.domain

import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkEntity
import com.nestozo.enriq.apptragos.vo.Resource

interface Repo {
    suspend fun getDrinkList(drinkName: String): Resource<List<Drink>>
    suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>>
    suspend fun insertDrink(drink:DrinkEntity)
    suspend fun deleteDrink(drink: DrinkEntity)
}