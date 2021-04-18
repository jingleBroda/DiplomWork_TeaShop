package com.example.teashop_v1.ui.personalArea

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalAreaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Это ваш личный кабинет."
    }
    val text: LiveData<String> = _text
}