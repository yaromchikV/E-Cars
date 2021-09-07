package com.yaromchikv.ecars.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.yaromchikv.ecars.database_room.CarDatabase
import com.yaromchikv.ecars.repository.CarRepository
import com.yaromchikv.ecars.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Car>>
    private val repository: CarRepository

    init {
        val carDao = CarDatabase.getDatabase(application).carDao()
        repository = CarRepository(carDao)
        readAllData = repository.readAllData
    }

    fun addCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCar(car)
        }
    }

    fun updateCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCar(car)
        }
    }
}