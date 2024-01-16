package com.example.university_schedule.apiRest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiSendLesson {
    @POST("/strings")
    fun sendLesson(@Body item: MutableList<String>,@Query("id") id: String) : Call<Void>
}