package com.example.university_schedule.week

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.university_schedule.adapter.RecycleAdapterItem
import com.example.university_schedule.apiRest.FetchDataFromServer
import com.example.university_schedule.databinding.FragmentMondayBinding
import com.example.university_schedule.dto.ItemData
import com.example.university_schedule.fetchData
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
    private lateinit var list: ItemData
    private var selectedItem  = mutableListOf<String>()
    private var selectedValues = Triple<String, String, String>("","","")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMondayBinding.inflate(inflater, container, false)
        itemAdapter = RecycleAdapterItem(mutableListOf())
        fetchData(requireContext(),itemAdapter){
            list = it
            itemAdapter.addItem(list.lessonName, list.lessonTime,list.lessonPractics)
        }
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.addLesson.setOnClickListener {
                itemAdapter.addItem(list.lessonName, list.lessonTime,list.lessonPractics)
        }
        recyclerView = binding.recyclerView
        binding.saveLesson.setOnClickListener {
            selectedItem.clear()
            for (i in 0 until itemAdapter.itemCount) {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as RecycleAdapterItem.ViewHolder?
                if (viewHolder != null) {
                    selectedValues = viewHolder.getSelectedValues()
                    Log.d("AUTO", selectedValues.toString())
                    selectedItem.add(selectedValues.toString())
                }
            }
            sendLessonData(requireContext(),selectedItem)
            Log.d("LIST", selectedItem.toString())
        }
        return binding.root
    }

    fun shared(){
        val shared = context?.getSharedPreferences("ServerId", Context.MODE_PRIVATE)
        var editor = shared?.getString("serverId" , "")
        if (editor?.isEmpty() == true || editor?.isBlank() == true){
            editor = "http://192.168.246.171:8080/"
        }
    }
}