package com.yaromchikv.ecars.database_room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yaromchikv.ecars.model.Car

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCar(car: Car)

    @Update
    suspend fun updateCar(car: Car)

    @Query("SELECT * FROM cars_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Car>>

}