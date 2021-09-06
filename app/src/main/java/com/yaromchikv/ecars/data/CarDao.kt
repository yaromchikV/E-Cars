package com.yaromchikv.ecars.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCar(car: Car)

    @Query("SELECT * FROM cars_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Car>>

}