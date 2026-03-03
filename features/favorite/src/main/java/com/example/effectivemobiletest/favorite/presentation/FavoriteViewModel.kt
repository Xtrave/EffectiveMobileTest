package com.example.effectivemobiletest.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class FavoriteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is favorite Fragment"
    }
    val text: LiveData<String> = _text
}