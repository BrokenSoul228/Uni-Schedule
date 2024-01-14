package com.example.university_schedule.apiRest

import retrofit2.Call
import retrofit2.http.GET

interface LoginApi {
    @GET("/auth")
    fun sendLoginData(login : String, password : String) : Call<String>
}