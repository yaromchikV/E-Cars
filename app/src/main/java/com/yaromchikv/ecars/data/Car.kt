package com.yaromchikv.ecars.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class Car(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    val name: String,
    val acceleration: Double,
    val price: Double
)