package com.example.university_schedule.week

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.university_schedule.R
import com.example.university_schedule.adapter.RecycleAdapterItem
import com.example.university_schedule.apiRest.FetchDataFromServer
import com.example.university_schedule.databinding.FragmentThuesdayBinding
import com.example.university_schedule.dto.ItemData
import com.example.university_schedule.sendLessonData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ThuesdayFragment : Fragment() {

    private lateinit var itemAdapter : RecycleAdapterItem
    private lateinit var binding: FragmentThuesdayBinding
    val listName = listOf<String>("ПДИ", "ПМС")
    val listTime = listOf<String>("8:00", "9:00")
    val listPrac = listOf<String>("Лекция", "Практика", "Лабораторная")
    private lateinit var list: List<ItemData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThuesdayBinding.inflate(inflater, container, false)
        itemAdapter = RecycleAdapterItem(mutableListOf())
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val listName = listOf<String>("ПДИ", "ПМС")
        val listTime = listOf<String>("8:00", "9:00")
        val listPrac = listOf<String>("Лекция", "Практика", "Лабораторная")
        list = List(listName.size) {
            ItemData(listName, listTime, listPrac)
        }
        binding.addLesson.setOnClickListener {
//            itemAdapter.addItem()
        }
        binding.saveLesson.setOnClickListener {
//            sendLessonData(requireContext(), itemAdapter.getItemList())
            itemAdapter.updateData(list)
        }
        return binding.root
    }
}