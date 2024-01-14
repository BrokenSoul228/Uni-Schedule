package com.example.university_schedule.week

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.university_schedule.apiRest.FetchDataFromServer
import com.example.university_schedule.adapter.RecycleAdapterItem
import com.example.university_schedule.databinding.FragmentMondayBinding
import com.example.university_schedule.dto.ItemData
import com.example.university_schedule.sendLessonData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MondayFragment : Fragment() {

    private lateinit var binding: FragmentMondayBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter : RecycleAdapterItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMondayBinding.inflate(inflater, container, false)
        itemAdapter = RecycleAdapterItem(mutableListOf())
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        //fetchData()
        binding.addLesson.setOnClickListener {
            itemAdapter.addItem()
        }
        binding.saveLesson.setOnClickListener {
            sendLessonData(requireContext(), itemAdapter.getItemList())
        }
        return binding.root
    }

    fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchDataFromServer::class.java)

        val call : Call<List<ItemData>> = retrofit.fetchData()
        
        call.enqueue(object : Callback<List<ItemData>>{
            override fun onResponse(
                call: Call<List<ItemData>>,
                response: Response<List<ItemData>>
            ) {
                if(response.isSuccessful){
                    val serverResponse = response.body()
                    serverResponse?.let {
                        itemAdapter.updateData(it)
                        Toast.makeText(context, "Обновлено", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<ItemData>>, t: Throwable) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}