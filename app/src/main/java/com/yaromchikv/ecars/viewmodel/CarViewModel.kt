package com.yaromchikv.ecars.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.yaromchikv.ecars.database.CarRepository
import com.yaromchikv.ecars.database.room.CarDatabase
import com.yaromchikv.ecars.database.sqilte.DatabaseHelper
import com.yaromchikv.ecars.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private val carDao = CarDatabase.getDatabase(application).carDao()
    private val carDatabaseHelper = DatabaseHelper(application)
    private val prefs = PreferenceManager.getDefaultSharedPreferences(application)

    private val repository = CarRepository(carDao, carDatabaseHelper, prefs)

    var allDataFromRoom: LiveData<List<Car>> = repository.allData

    var sortBy = "name"
    var sortOrder = "ASC"

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

    fun getDataUsingCursors(): List<Car> {
        return repository.getDataUsingCursors(sortBy, sortOrder)
    }

    fun changeDaoQuery() {
        allDataFromRoom = repository.changeDaoQuery(sortBy, sortOrder)
    }
}