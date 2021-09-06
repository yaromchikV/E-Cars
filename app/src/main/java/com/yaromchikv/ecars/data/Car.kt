package com.yaromchikv.ecars.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class Car(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    //@ColumnInfo(name = "name")
    val name: String,

    //@ColumnInfo(name = "description")
    val description: String,

    //@ColumnInfo(name = "price")
    val price: Double
)