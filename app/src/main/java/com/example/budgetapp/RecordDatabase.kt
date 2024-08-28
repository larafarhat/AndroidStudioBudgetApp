package com.example.budgetapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.budgetapp.Record

@Database(entities = [Record::class], version = 1, exportSchema = false)

abstract class RecordDatabase : RoomDatabase() {
    abstract val recordDao: RecordDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null
        fun getInstance(context: Context): RecordDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, RecordDatabase::class.java, "record_database"
                    ).build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}

