package com.example.university_schedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.university_schedule.dto.ItemData
import com.example.university_schedule.R

class RecycleAdapterItem(private val itemList: MutableList<ItemData>) : RecyclerView.Adapter<RecycleAdapterItem.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val lessonNameEdit = itemView.findViewById<EditText>(R.id.lessonName)
        val lessonTimeEdit = itemView.findViewById<EditText>(R.id.lessonTime)
        val lessonPracticsEdit = itemView.findViewById<EditText>(R.id.lessonPracticsText)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecycleAdapterItem.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecycleAdapterItem.ViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.lessonNameEdit.setText(currentItem.lessonName)
        holder.lessonTimeEdit.setText(currentItem.lessonTime)
        holder.lessonPracticsEdit.setText(currentItem.lessonPractics)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(){
        val newPosition = itemCount
        itemList.add(ItemData(newPosition,"","",""))
        notifyItemInserted(itemList.size - 1)
    }

    fun getItemList() : List<ItemData>{
        return itemList
    }

    fun updateData(newData : List<ItemData>){
        itemList.clear()
        itemList.addAll(newData)
        notifyDataSetChanged()
    }
}