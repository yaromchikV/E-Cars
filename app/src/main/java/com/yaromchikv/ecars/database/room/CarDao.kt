package com.yaromchikv.ecars.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yaromchikv.ecars.database.*
import com.yaromchikv.ecars.model.Car

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCar(car: Car)

    @Update
    suspend fun updateCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)

    @Query(QUERY_ORDER_BY_NAME_ASC)
    fun getAllSortedByNameInAscendingOrder(): LiveData<List<Car>>

    @Query(QUERY_ORDER_BY_NAME_DESC)
    fun getAllSortedByNameInDescendingOrder(): LiveData<List<Car>>

    @Query(QUERY_ORDER_BY_ACCELERATION_ASC)
    fun getAllSortedByAccelerationInAscendingOrder(): LiveData<List<Car>>

    @Query(QUERY_ORDER_BY_ACCELERATION_DESC)
    fun getAllSortedByAccelerationInDescendingOrder(): LiveData<List<Car>>

    @Query(QUERY_ORDER_BY_RANGE_ASC)
    fun getAllSortedByRangeInAscendingOrder(): LiveData<List<Car>>

    @Query(QUERY_ORDER_BY_RANGE_DESC)
    fun getAllSortedByRangeInDescendingOrder(): LiveData<List<Car>>

    @Query(QUERY_ORDER_BY_PRICE_ASC)
    fun getAllSortedByPriceInAscendingOrder(): LiveData<List<Car>>

    @Query(QUERY_ORDER_BY_PRICE_DESC)
    fun getAllSortedByPriceInDescendingOrder(): LiveData<List<Car>>

}