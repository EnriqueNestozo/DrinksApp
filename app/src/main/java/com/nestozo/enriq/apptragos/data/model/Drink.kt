package com.nestozo.enriq.apptragos.data.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val drinkId: String = "",
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol:String = "Non_Alcoholic"
):Parcelable

data class DrinkList(@SerializedName("drinks") val drinkList: List<Drink>)

@Entity(tableName = "drink_entity")
data class DrinkEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "drink_img")
    val imagen: String = "",
    @ColumnInfo(name = "drink_name")
    val nombre: String = "",
    @ColumnInfo(name = "drink_description")
    val descripcion: String = "",
    @ColumnInfo(name = "drink_has_alcohol")
    val hasAlcohol:String = "Non_Alcoholic"
)