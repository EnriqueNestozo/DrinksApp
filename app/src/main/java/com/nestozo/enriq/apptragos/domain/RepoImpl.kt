package com.nestozo.enriq.apptragos.domain

import com.nestozo.enriq.apptragos.data.DataSource
import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override suspend fun getDrinkList(drinkName: String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }
}