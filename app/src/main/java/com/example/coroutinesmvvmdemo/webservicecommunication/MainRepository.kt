package com.example.coroutinesmvvmdemo.webservicecommunication

import com.example.coroutinesmvvmdemo.model.User

class MainRepository(private val  apiHelper: ApiHelper) {

    suspend fun getUsers() : List<User>{
       return apiHelper.getUsers()
    }
}