package com.nestozo.enriq.apptragos.vo

import com.google.gson.GsonBuilder
import com.nestozo.enriq.apptragos.domain.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webservice by lazy {
        //crea una instancia de retrofit con una url base, con un covertidor que va a transformar
        // el json y va a crear la instancia con la interfaz webservice, la cual hará todas las peticiones
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}