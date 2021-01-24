package com.nestozo.enriq.apptragos.domain

import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkEntity
import com.nestozo.enriq.apptragos.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override suspend fun getDrinkList(drinkName: String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>> {
        return dataSource.getFavoritesDrinks()
    }

    override suspend fun insertDrink(drink: DrinkEntity) {
        dataSource.insertDrinkIntoRoom(drink)
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        dataSource.deleteDrink(drink)
    }
}