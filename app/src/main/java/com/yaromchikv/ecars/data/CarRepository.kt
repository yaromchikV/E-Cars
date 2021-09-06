package com.yaromchikv.ecars.data

import androidx.lifecycle.LiveData

class CarRepository(private val carDao: CarDao) {

    val readAllData: LiveData<List<Car>> = carDao.getAllData()

    suspend fun addCar(car: Car) {
        carDao.addCar(car)
    }
}