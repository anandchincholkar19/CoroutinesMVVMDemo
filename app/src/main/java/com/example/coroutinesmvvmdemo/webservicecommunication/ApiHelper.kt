package com.example.coroutinesmvvmdemo.webservicecommunication

import com.example.coroutinesmvvmdemo.model.User

interface ApiHelper {
    suspend fun getUsers() : ArrayList<User>
}