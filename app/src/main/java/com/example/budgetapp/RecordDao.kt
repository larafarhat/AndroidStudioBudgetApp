package com.example.budgetapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDao {

    @Insert
    suspend fun insert(record: Record)

    @Query("SELECT * FROM record_table ORDER BY recordId DESC")
    fun getAll(): LiveData<List<Record>>


}