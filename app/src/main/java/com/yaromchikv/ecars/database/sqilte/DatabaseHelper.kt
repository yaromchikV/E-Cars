package com.yaromchikv.ecars.database.sqilte

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.yaromchikv.ecars.database.*
import com.yaromchikv.ecars.model.Car

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_ACCELERATION REAL, $COLUMN_PRICE INTEGER, $COLUMN_IMAGE INTEGER);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getCursorWithData(sortBy: String, sortOrder: String): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $sortBy $sortOrder"
        return readableDatabase?.rawQuery(query, null)
    }

    private fun getFilledContentValues(car: Car): ContentValues {
        return ContentValues().apply {
            put(COLUMN_NAME, car.name)
            put(COLUMN_ACCELERATION, car.acceleration)
            put(COLUMN_PRICE, car.price)
            put(COLUMN_IMAGE, car.image)
        }
    }

    fun addCar(car: Car) {
        val cv = getFilledContentValues(car)
        writableDatabase.insert(TABLE_NAME, null, cv)
    }

    fun updateCar(car: Car) {
        val cv = getFilledContentValues(car)
        writableDatabase.update(TABLE_NAME, cv, "$COLUMN_ID=?", Array(1) { "${car.id}" })
    }

    fun deleteCar(car: Car) {
        writableDatabase.delete(TABLE_NAME, "$COLUMN_ID=?", Array(1) { "${car.id}" })
    }
}