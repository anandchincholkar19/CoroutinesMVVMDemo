package com.example.coroutinesmvvmdemo.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinesmvvmdemo.webservicecommunication.ApiHelper
import com.example.coroutinesmvvmdemo.webservicecommunication.MainRepository

class UserViewModelFactory(private val apiHelper: ApiHelper) :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}