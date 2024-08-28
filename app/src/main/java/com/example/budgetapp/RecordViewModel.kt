package com.example.budgetapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.Record
import kotlinx.coroutines.launch

class RecordViewModel(private val dao: RecordDao) : ViewModel() {

//    var newRecord =  ""

    val record = dao.getAll()

//    fun addRecord() {
//        viewModelScope.launch {
//            val record = Record()
//            record.recordAmount = newRecord
//
//            dao.insert(record)
//        }
//    }
}