package com.yaromchikv.ecars.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yaromchikv.ecars.R
import com.yaromchikv.ecars.database.DATABASE_NAME
import com.yaromchikv.ecars.database.DATABASE_VERSION
import com.yaromchikv.ecars.model.Car
import java.util.concurrent.Executors

@Database(entities = [Car::class], version = DATABASE_VERSION, exportSchema = false)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        @Volatile
        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getDatabase(context).carDao().addListOfCars(PREPOPULATE_DATA)
                        }
                    }
                }).build()
                INSTANCE = instance
                return instance
            }
        }

        fun ioThread(f: () -> Unit) {
            Executors.newSingleThreadExecutor().execute(f)
        }

        val PREPOPULATE_DATA = listOf(
            Car(0, "Tesla Model X", 2.6, 547, 113190, R.drawable.car1),
            Car(0, "Tesla Model 3", 3.3, 614, 44190, R.drawable.car2),
            Car(0, "Tesla Model Y", 3.5, 486, 55190, R.drawable.car3),
            Car(0, "Audi e-tron GT", 3.5, 487, 75000, R.drawable.car4),
            Car(0, "Porsche Taycan", 2.8, 417, 79900, R.drawable.car5),
            Car(0, "Volvo XC40", 4.9, 418, 59995, R.drawable.car6),
            Car(0, "Volkswagen ID.4", 8.5, 419, 39990, R.drawable.car1),
        )
    }
}