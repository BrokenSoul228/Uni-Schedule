package com.example.university_schedule

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.university_schedule.apiRest.LoginApi
import com.example.university_schedule.dto.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitLogin {
    fun accessLogin(login : String, password : String,context : Context, callback: (String) -> Unit) {
        val shared = context.getSharedPreferences("ServerId", Context.MODE_PRIVATE)
        var editor = shared?.getString("serverId" , "")
        if (editor?.isEmpty() == true || editor?.isBlank() == true){
            editor = "http://192.168.246.216:8080"
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("$editor")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val apiService = retrofit.create(LoginApi::class.java)

        val call: Call<String> = apiService.sendLoginData(login , password)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val resp = response.body()
                    Log.d("LoginServer", "Response: $response")
                    callback(resp.toString())
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("LoginServer", "Unsuccessful response. Code: ${response.code()}, Error: $errorBody")
                    callback("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Server", call.toString())
                Log.d("LoginServer", "message", t)
            }
        })
    }
}