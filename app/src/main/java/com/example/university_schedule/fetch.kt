package com.example.university_schedule

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.university_schedule.adapter.RecycleAdapterItem
import com.example.university_schedule.apiRest.FetchDataFromServer
import com.example.university_schedule.dto.ItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun fetchData(context : Context, itemAdapter : RecycleAdapterItem, callback: (ItemData) -> Unit) {
    val shared = context.getSharedPreferences("ServerId", Context.MODE_PRIVATE)
    var editor = shared?.getString("serverId" , "")
    if (editor?.isEmpty() == true || editor?.isBlank() == true){
        editor = "http://192.168.246.216:8080"
    }
    val retrofit = Retrofit.Builder()
        .baseUrl("$editor")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FetchDataFromServer::class.java)

    val call : Call<ItemData> = retrofit.fetchData()

    call.enqueue(object : Callback<ItemData> {
        override fun onResponse(
            call: Call<ItemData>,
            response: Response<ItemData>
        ) {
            if(response.isSuccessful){
                val serverResponse = response.body()
                serverResponse?.let {
                    callback(serverResponse)
                    Toast.makeText(context, "Обновлено", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onFailure(call: Call<ItemData>, t: Throwable) {
            Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show()
            Log.d("DATASERVER", "MESSAGE", t)
        }
    })
}