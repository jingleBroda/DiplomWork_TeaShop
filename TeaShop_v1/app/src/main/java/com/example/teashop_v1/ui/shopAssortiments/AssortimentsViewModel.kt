package com.example.teashop_v1.ui.shopAssortiments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AssortimentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Тут будет список товаров, которые есть в наличиии на данный момент."
    }
    val text: LiveData<String> = _text
}