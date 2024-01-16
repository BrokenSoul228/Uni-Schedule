package com.example.university_schedule.apiRest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {
    @GET("auth")
    fun sendLoginData(@Query("login") login: String, @Query("password") password: String) : Call<String>
}
