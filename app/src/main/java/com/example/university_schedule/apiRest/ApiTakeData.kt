package com.example.university_schedule.apiRest

import retrofit2.Call
import com.example.university_schedule.dto.ItemData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTakeData {
    @GET("/timetables")
    fun takeData(@Query("id") id : String) : Call<List<String>>
}