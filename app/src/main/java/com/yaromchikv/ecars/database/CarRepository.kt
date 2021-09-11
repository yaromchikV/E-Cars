package com.yaromchikv.ecars.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import com.yaromchikv.ecars.database.room.CarDao
import com.yaromchikv.ecars.database.room.CarDatabase
import com.yaromchikv.ecars.database.sqilte.DatabaseHelper
import com.yaromchikv.ecars.model.Car

internal const val DATABASE_NAME = "cars_database"
internal const val DATABASE_VERSION = 1
internal const val TABLE_NAME = "cars_table"

internal const val COLUMN_ID = "id"
internal const val COLUMN_NAME = "name"
internal const val COLUMN_ACCELERATION = "acceleration"
internal const val COLUMN_PRICE = "price"
internal const val COLUMN_IMAGE = "image"

internal const val QUERY_ORDER_BY_NAME_ASC = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_NAME ASC"
internal const val QUERY_ORDER_BY_NAME_DESC = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_NAME DESC"
internal const val QUERY_ORDER_BY_ACCELERATION_ASC = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ACCELERATION ASC"
internal const val QUERY_ORDER_BY_ACCELERATION_DESC = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ACCELERATION DESC"
internal const val QUERY_ORDER_BY_PRICE_ASC = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_PRICE ASC"
internal const val QUERY_ORDER_BY_PRICE_DESC = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_PRICE DESC"

class CarRepository(context: Context) {

    private var carDatabase = DatabaseHelper(context)
    private var carDao: CarDao = CarDatabase.getDatabase(context).carDao()

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    val allData: LiveData<List<Car>> = getDataUsingRoom(
        prefs.getString("sort_by", "name") ?: "name",
        prefs.getString("sort_order", "ASC") ?: "ASC"
    )

    suspend fun addCar(car: Car) {
        val useRoom = prefs.getBoolean("implementation", true)
        if (useRoom)
            carDao.addCar(car)
        else
            carDatabase.addCar(car)
    }

    suspend fun updateCar(car: Car) {
        val useRoom = prefs.getBoolean("implementation", true)
        if (useRoom)
            carDao.updateCar(car)
        else
            carDatabase.updateCar(car)
    }

    suspend fun deleteCar(car: Car) {
        val useRoom = prefs.getBoolean("implementation", true)
        if (useRoom)
            carDao.deleteCar(car)
        else
            carDatabase.deleteCar(car)
    }

    fun getDataUsingCursors(sortBy: String, sortOrder: String): List<Car> {
        val listOfCar = mutableListOf<Car>()
        val cursor = carDatabase.getCursorWithData(sortBy, sortOrder)
        if (cursor != null && cursor.count != 0) {
            while (cursor.moveToNext()) {
                listOfCar.add(
                    Car(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
                    )
                )
            }
        }
        cursor?.close()
        return listOfCar
    }

    private fun getDataUsingRoom(sortBy: String, sortOrder: String): LiveData<List<Car>> {
        return when ("SELECT * FROM $TABLE_NAME ORDER BY $sortBy $sortOrder") {
            QUERY_ORDER_BY_NAME_DESC -> carDao.getAllSortedByNameInDescendingOrder()
            QUERY_ORDER_BY_ACCELERATION_ASC -> carDao.getAllSortedByAccelerationInAscendingOrder()
            QUERY_ORDER_BY_ACCELERATION_DESC -> carDao.getAllSortedByAccelerationInDescendingOrder()
            QUERY_ORDER_BY_PRICE_ASC -> carDao.getAllSortedByPriceInAscendingOrder()
            QUERY_ORDER_BY_PRICE_DESC -> carDao.getAllSortedByPriceInDescendingOrder()
            else -> carDao.getAllSortedByNameInAscendingOrder()
        }
    }
}