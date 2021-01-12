package com.nestozo.enriq.apptragos.data.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    val imagen: String = "",
    val nombre: String = "",
    val descripcion: String = ""
):Parcelable