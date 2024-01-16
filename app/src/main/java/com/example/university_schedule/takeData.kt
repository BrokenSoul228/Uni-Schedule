package com.example.university_schedule

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.university_schedule.adapter.RecycleAdapterItem
import com.example.university_schedule.apiRest.ApiSendLesson
import com.example.university_schedule.apiRest.ApiTakeData
import com.example.university_schedule.dto.ItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun takeData(context : Context,id : String, callback: (List<String>) -> Unit) {
    val shared = context.getSharedPreferences("ServerId", Context.MODE_PRIVATE)
    var editor = shared?.getString("serverId" , "")
    if (editor?.isEmpty() == true || editor?.isBlank() == true){
        editor = "http://192.168.246.216:8080"
    }
    Log.d("SERVERID", editor.toString())
    val retrofit = Retrofit.Builder()
        .baseUrl("$editor")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiTakeData::class.java)
    val call: Call<List<String>> = retrofit.takeData(id)
    call.enqueue(object : Callback<List<String>> {
        override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
            if (response.isSuccessful){
                val serverResponse = response.body()
                callback(serverResponse?: emptyList())
                Toast.makeText(context, "Обновлено", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            Toast.makeText(context, "Не обновлено", Toast.LENGTH_SHORT).show()
            Log.d("NOTUP", "MESSAGE", t)
        }

    })
}