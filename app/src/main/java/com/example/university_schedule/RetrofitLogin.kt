package com.example.university_schedule

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.university_schedule.apiRest.LoginApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitLogin {
    fun accessLogin(login : String, password : String, context: Context, callback: (String) -> Unit){
        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(LoginApi::class.java)

        val call : Call<String> = retrofit.sendLoginData(login, password)

        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    val resp = response.body()
                    callback(resp.toString())
                    Toast.makeText(context, "Вход выполнен", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(context, "Вход не выполнен", Toast.LENGTH_SHORT).show()
                Log.d("LoginServer", t.message.toString())
            }
        })
    }
}