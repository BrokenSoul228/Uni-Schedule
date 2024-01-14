package com.example.university_schedule.apiRest

import android.telecom.Call
import com.example.university_schedule.dto.ItemData
import com.example.university_schedule.dto.ServerResponse
import retrofit2.http.GET

interface FetchDataFromServer {
    @GET("fetchData")
    fun fetchData(): retrofit2.Call<List<ItemData>>
}