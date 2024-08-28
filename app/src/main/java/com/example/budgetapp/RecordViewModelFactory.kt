package com.example.budgetapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budgetapp.Record

class RecordViewModelFactory(private val dao: RecordDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordViewModel::class.java)) {
            return RecordViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}