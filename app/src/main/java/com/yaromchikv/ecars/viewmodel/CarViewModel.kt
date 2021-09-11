package com.yaromchikv.ecars.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.yaromchikv.ecars.R
import com.yaromchikv.ecars.database.CarRepository
import com.yaromchikv.ecars.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

    var allDataFromRoom: LiveData<List<Car>>

    private val repository: CarRepository = CarRepository(application)

    init {
        allDataFromRoom = repository.allData
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

    fun deleteCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCar(car)
        }
    }

    fun getDataUsingCursors(sortBy: String, sortOrder: String): List<Car> {
        return repository.getDataUsingCursors(sortBy, sortOrder)
    }

    fun getRandomImage(): Int {
        return listOf(
            R.drawable.car1,
            R.drawable.car2,
            R.drawable.car3,
            R.drawable.car4,
            R.drawable.car5,
            R.drawable.car6,
        ).random()
    }
}