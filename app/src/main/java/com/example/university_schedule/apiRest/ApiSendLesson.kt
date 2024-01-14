package com.example.university_schedule.apiRest

import com.example.university_schedule.dto.ItemData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiSendLesson {
    @POST
    fun sendLesson(@Body data: List<ItemData>) : Call<Void>
}