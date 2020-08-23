package com.example.coroutinesmvvmdemo.webservicecommunication
import com.example.coroutinesmvvmdemo.model.User

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): ArrayList<User>
}