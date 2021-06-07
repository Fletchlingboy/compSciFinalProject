package com.cummings.honorsmobileapp.todolistapp

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RoutineItemAdapter(val routineList: List<RoutineItem>, sharedPrefs: SharedPreferences): RecyclerView.Adapter<RoutineItemViewHolder>() {
    val sharedPreferences=sharedPrefs
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineItemViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val rootView=layoutInflater.inflate(R.layout.list_item_layout,parent,false)
        val newViewHolder=RoutineItemViewHolder(rootView)
        return newViewHolder
    }

    override fun onBindViewHolder(holder: RoutineItemViewHolder, position: Int) {
        val currentRoutineItem=routineList[position]
        holder.bindRoutineItem(currentRoutineItem, sharedPreferences)

    }

    override fun getItemCount(): Int {
        return routineList.size
    }

}