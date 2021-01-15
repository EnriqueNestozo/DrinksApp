package com.nestozo.enriq.apptragos.domain

import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.vo.Resource

interface Repo {
    suspend fun getDrinkList(drinkName: String): Resource<List<Drink>>
}