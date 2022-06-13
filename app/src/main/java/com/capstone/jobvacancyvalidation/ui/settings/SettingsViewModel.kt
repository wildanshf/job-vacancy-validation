package com.capstone.jobvacancyvalidation.ui.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()

    val name: LiveData<String>
        get() = _name

    val email: LiveData<String>
        get() = _email


    init {
        Log.d("home-viewmodel", "init home view model!")
        _name.value = "Name"
        _email.value = "email@mail.com"
    }

    fun setUserData(name: String, email: String) {
        _name.value = name
        _email.value = email
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}