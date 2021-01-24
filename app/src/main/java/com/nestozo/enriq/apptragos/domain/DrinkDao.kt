package com.nestozo.enriq.apptragos.domain

import androidx.room.*
import com.nestozo.enriq.apptragos.data.model.Drink
import com.nestozo.enriq.apptragos.data.model.DrinkEntity

@Dao
interface DrinksDao {
    @Query("SELECT * FROM drink_entity")
    suspend fun getAllFavoritesDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drink: DrinkEntity)


}