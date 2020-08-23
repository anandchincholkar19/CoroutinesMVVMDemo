package com.example.coroutinesmvvmdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coroutinesmvvmdemo.model.User
import com.example.coroutinesmvvmdemo.utils.Resource
import com.example.coroutinesmvvmdemo.webservicecommunication.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class UserViewModel(private val mainRepository: MainRepository): ViewModel() {

    fun getUSers() =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            try {
                emit(Resource.success(mainRepository.getUsers()))
            } catch (exception: Exception) {
                emit(Resource.error(null,exception.message?: "Error Occured !!!!"))
            }
    }
}