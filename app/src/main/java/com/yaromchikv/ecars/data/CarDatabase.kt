package com.yaromchikv.ecars.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        @Volatile
        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "car_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}