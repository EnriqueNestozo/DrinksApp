package com.nestozo.enriq.apptragos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nestozo.enriq.apptragos.data.model.DrinkEntity
import com.nestozo.enriq.apptragos.domain.DrinksDao

@Database(entities = arrayOf(DrinkEntity::class),version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun drinkDao(): DrinksDao

    companion object{
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "drink_entity").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}