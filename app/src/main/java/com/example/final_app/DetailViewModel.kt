package com.example.final_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.final_app.model.DataClass

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _data = MutableLiveData<DataClass>()
    val data: LiveData<DataClass> get() = _data

    fun setData(dataClass: DataClass) {
        _data.value = dataClass
    }
}
