package com.yaromchikv.ecars.repository

import androidx.lifecycle.LiveData
import com.yaromchikv.ecars.database_room.CarDao
import com.yaromchikv.ecars.model.Car

class CarRepository(private val carDao: CarDao) {

    val readAllData: LiveData<List<Car>> = carDao.getAllData()

    suspend fun addCar(car: Car) {
        carDao.addCar(car)
    }

    suspend fun updateCar(car: Car) {
        carDao.updateCar(car)
    }
}