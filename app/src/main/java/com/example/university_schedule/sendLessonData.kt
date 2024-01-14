package com.example.university_schedule

import android.content.Context
import android.widget.Toast
import com.example.university_schedule.apiRest.ApiSendLesson
import com.example.university_schedule.dto.ItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun sendLessonData(context : Context, dataList: List<ItemData>) {
    val retrofit = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiSendLesson::class.java)
    val call: Call<Void> = retrofit.sendLesson(dataList)
    call.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Toast.makeText(context, "Не успешно", Toast.LENGTH_SHORT).show()
        }
    })
}