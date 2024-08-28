package com.example.budgetapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar


@Entity(tableName = "record_table")
data class Record(
    @PrimaryKey(autoGenerate = true) var recordId: Long = 0L,
    @ColumnInfo(name = "record_type") var recordType: String = "",
    @ColumnInfo(name = "record_amount") var recordAmount: String = "",
    @ColumnInfo(name = "record_category") var recordCategory: String = "",
    @ColumnInfo(name = "record_date") var recordDate: String = "",
    @ColumnInfo(name = "record_note") var recordNote: String = "",

)
