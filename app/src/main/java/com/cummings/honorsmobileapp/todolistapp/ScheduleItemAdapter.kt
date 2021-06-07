package com.cummings.honorsmobileapp.todolistapp

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ScheduleItemAdapter(val scheduleList: List<ScheduleItem>, sharedPrefs: SharedPreferences): RecyclerView.Adapter<ScheduleItemViewHolder>() {
    val sharedPreferences=sharedPrefs
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val rootView=layoutInflater.inflate(R.layout.list_item_layout,parent,false)
        val newViewHolder=ScheduleItemViewHolder(rootView)
        return newViewHolder
    }

    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {
        val currentScheduleItem=scheduleList[position]
        holder.bindScheduleItem(currentScheduleItem, sharedPreferences)

    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

}