package com.example.university_schedule.week

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.university_schedule.adapter.RecycleAdapterItem
import com.example.university_schedule.databinding.FragmentThuesdayBinding
import com.example.university_schedule.dto.ItemData
import com.example.university_schedule.func.fetchData
import com.example.university_schedule.sendLessonData
import com.example.university_schedule.takeData

class ThuesdayFragment : Fragment() {

    private lateinit var binding: FragmentThuesdayBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter : RecycleAdapterItem
    private lateinit var list: ItemData
    private lateinit var listData: List<ItemData>
    private var selectedItem  = mutableListOf<String>()
    private var selectedValues = Triple<String, String, String>("","","")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThuesdayBinding.inflate(inflater, container, false)
        val shared = context?.getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val editor = shared?.getString("ID", "")
        when(editor.toString()){
            "0" -> {itemAdapter = RecycleAdapterItem(true, mutableListOf())}
            "1" -> {itemAdapter = RecycleAdapterItem(true, mutableListOf())}
            "2" -> {itemAdapter = RecycleAdapterItem(false, mutableListOf())
                binding.addLesson.isVisible = false
                binding.saveLesson.isVisible = false}
        }
        listData = listOf()
        when(editor.toString()){
            "2" -> {takeData(requireContext(),"tuesday") { data ->
                val items = mutableListOf<ItemData>()
                for (i in data) {
                    val columns = i.split(",")
                    val item = ItemData(mutableListOf(columns[0]), mutableListOf(columns[2]), mutableListOf(columns[1]))
                    items.add(item)
                }
                itemAdapter.updateData(items)
            }}
            else -> {
                fetchData(requireContext(),itemAdapter){
                    list = it
                    itemAdapter.addItem(list.lessonName, list.lessonTime,list.lessonPractics)
                }
            }
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
            sendLessonData("tuesday",requireContext(),selectedItem)
            Log.d("LIST", selectedItem.toString())
        }
        return binding.root
    }
}