package com.yaromchikv.ecars.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cars_table")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,
    val acceleration: Double,
    val range: Int,
    val price: Int,
    val image: Int

) : Parcelable