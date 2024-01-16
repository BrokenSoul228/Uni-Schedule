package com.example.university_schedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.university_schedule.dto.ItemData
import com.example.university_schedule.R
import com.google.android.material.textfield.TextInputLayout

class RecycleAdapterItem(private var isButtonEnabled: Boolean, private val itemList: MutableList<ItemData>) : RecyclerView.Adapter<RecycleAdapterItem.ViewHolder>() {
    private var isItemUpdatedList: MutableList<Boolean> = MutableList(itemList.size) { false }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lessonNameEdit = itemView.findViewById<AutoCompleteTextView>(R.id.lessonName)
        val lessonTimeEdit = itemView.findViewById<AutoCompleteTextView>(R.id.lessonTime)
        val lessonPracticsEdit = itemView.findViewById<AutoCompleteTextView>(R.id.lessonPracticsText)

        fun getSelectedValues(): Triple<String, String, String> {
            return Triple(
                lessonNameEdit.text.toString(),
                lessonTimeEdit.text.toString(),
                lessonPracticsEdit.text.toString()
            )
        }
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
        val autoAdapterTime = ArrayAdapter(holder.itemView.context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, currentItem.lessonTime)
        val autoAdapterName = ArrayAdapter(holder.itemView.context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, currentItem.lessonName)
        val autoAdapterPrac = ArrayAdapter(holder.itemView.context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, currentItem.lessonPractics)
        if (isItemUpdatedList[position]) {
                holder.lessonNameEdit.setText(currentItem.lessonName.joinToString(separator = ","))
                holder.lessonTimeEdit.setText(currentItem.lessonTime.joinToString(separator = ","))
                holder.lessonPracticsEdit.setText(currentItem.lessonPractics.joinToString(separator = ","))
        }
        holder.lessonNameEdit.isEnabled = isButtonEnabled
        holder.lessonTimeEdit.isEnabled = isButtonEnabled
        holder.lessonPracticsEdit.isEnabled = isButtonEnabled

        holder.lessonNameEdit.setAdapter(autoAdapterName)
        holder.lessonTimeEdit.setAdapter(autoAdapterTime)
        holder.lessonPracticsEdit.setAdapter(autoAdapterPrac)
        isItemUpdatedList[position] = false
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(name: List<String>,time: List<String>,prac: List<String>){
        val newPosition = itemCount
        itemList.add(ItemData(name,time,prac))
        isItemUpdatedList.add(false)
        notifyItemInserted(itemList.size - 1)
    }

    fun getItemList() : List<ItemData>{
        return itemList
    }

    fun updateData(newData: List<ItemData>) {
        isItemUpdatedList.clear()
        isItemUpdatedList.addAll(List(newData.size) { true })
        itemList.clear()
        itemList.addAll(newData)
        notifyDataSetChanged()
    }
}