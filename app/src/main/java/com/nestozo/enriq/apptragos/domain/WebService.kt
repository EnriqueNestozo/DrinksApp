package com.nestozo.enriq.apptragos.domain

import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getDrinkByName(@Query("s") drinkName:String): DrinkList
}