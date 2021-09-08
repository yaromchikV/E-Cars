package com.yaromchikv.ecars.database_room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.yaromchikv.ecars.model.Car

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCar(car: Car)

    @Update
    suspend fun updateCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)

    @Query("SELECT * FROM cars_table ORDER BY name ASC")
    fun getAllSortedByNameInAscendingOrder(): LiveData<List<Car>>

    @Query("SELECT * FROM cars_table ORDER BY name DESC")
    fun getAllSortedByNameInDescendingOrder(): LiveData<List<Car>>

    @Query("SELECT * FROM cars_table ORDER BY acceleration ASC")
    fun getAllSortedByAccelerationInAscendingOrder(): LiveData<List<Car>>

    @Query("SELECT * FROM cars_table ORDER BY acceleration DESC")
    fun getAllSortedByAccelerationInDescendingOrder(): LiveData<List<Car>>

    @Query("SELECT * FROM cars_table ORDER BY price ASC")
    fun getAllSortedByPriceInAscendingOrder(): LiveData<List<Car>>

    @Query("SELECT * FROM cars_table ORDER BY price DESC")
    fun getAllSortedByPriceInDescendingOrder(): LiveData<List<Car>>

}