package com.yaromchikv.ecars.database_room

import androidx.lifecycle.LiveData
import com.yaromchikv.ecars.model.Car

class CarRepository(private val carDao: CarDao) {

    var allData: LiveData<List<Car>> = carDao.getAllSortedByNameInAscendingOrder()

    suspend fun addCar(car: Car) {
        carDao.addCar(car)
    }

    suspend fun updateCar(car: Car) {
        carDao.updateCar(car)
    }

    suspend fun deleteCar(car: Car) {
        carDao.deleteCar(car)
    }

    fun getSortCars(sortBy: String, sortOrder: String): LiveData<List<Car>> {
        return if (sortOrder == "Ascending") {
            when (sortBy) {
                "Acceleration" -> carDao.getAllSortedByAccelerationInAscendingOrder()
                "Price" -> carDao.getAllSortedByPriceInAscendingOrder()
                else -> carDao.getAllSortedByNameInAscendingOrder()
            }
        } else {
            when (sortBy) {
                "Acceleration" -> carDao.getAllSortedByAccelerationInDescendingOrder()
                "Price" -> carDao.getAllSortedByPriceInDescendingOrder()
                else -> carDao.getAllSortedByNameInDescendingOrder()
            }
        }
    }
}