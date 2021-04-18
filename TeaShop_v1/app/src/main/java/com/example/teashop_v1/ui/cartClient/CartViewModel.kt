package com.example.teashop_v1.ui.cartClient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Корзина в данный момент пуста."
    }
    val text: LiveData<String> = _text
}