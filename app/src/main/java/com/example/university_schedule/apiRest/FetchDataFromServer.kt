package com.example.university_schedule.apiRest

import android.telecom.Call
import com.example.university_schedule.dto.ItemData
import com.example.university_schedule.dto.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchDataFromServer {
    @GET("lists")
    fun fetchData(): retrofit2.Call<ItemData>
}