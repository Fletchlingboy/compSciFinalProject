package com.cummings.honorsmobileapp.todolistapp

import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoutineItemViewHolder(rootView: View): RecyclerView.ViewHolder(rootView) {
    private lateinit var currentRoutineItem: RoutineItem
    private val titleTextView: TextView =itemView.findViewById(R.id.textViewEventTitle)
    private val timeTextView: TextView =itemView.findViewById(R.id.textViewEventTime)
    private val deleteButton: Button =itemView.findViewById(R.id.deletebutton)

    fun bindRoutineItem(routine: RoutineItem, sharedPrefs: SharedPreferences){
        val is24Saved=sharedPrefs.getBoolean("IS_24_HOUR", false)
        currentRoutineItem=routine
        titleTextView.text=currentRoutineItem.title
        var hoursFormat=currentRoutineItem.hours
        var timeOfDay = ""
        if(!is24Saved) {
            timeOfDay = "AM"
            if (hoursFormat - 12 > 0) {
                hoursFormat -= 12
                timeOfDay = "PM"
            }
            else if(hoursFormat==12){
                timeOfDay="PM"
            }
        }
        var minutesFormat=currentRoutineItem.minutes
        var minutesText="${currentRoutineItem.minutes}"
        if(10-minutesFormat>0){
            minutesText="0$minutesText"
        }
        timeTextView.text="$hoursFormat:$minutesText$timeOfDay"

        deleteButton.setOnClickListener(){
            var position=adapterPosition

        }
    }
}