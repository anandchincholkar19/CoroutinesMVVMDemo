package com.example.coroutinesmvvmdemo.webservicecommunication

import com.example.coroutinesmvvmdemo.model.User

class ApiHelperImpl(private val apiService: ApiService) :ApiHelper {

    override suspend fun getUsers(): ArrayList<User> {
        return apiService.getUsers()
    }
}