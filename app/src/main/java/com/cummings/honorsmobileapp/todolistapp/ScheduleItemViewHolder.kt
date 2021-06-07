package com.cummings.honorsmobileapp.todolistapp

import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScheduleItemViewHolder(rootView: View): RecyclerView.ViewHolder(rootView) {
    private lateinit var currentScheduleItem: ScheduleItem
    private val titleTextView: TextView =itemView.findViewById(R.id.textViewEventTitle)
    private val timeTextView: TextView =itemView.findViewById(R.id.textViewEventTime)
    private val deleteButton: Button =itemView.findViewById(R.id.deletebutton)

    fun bindScheduleItem(schedule: ScheduleItem, sharedPrefs: SharedPreferences){
        val is24Saved=sharedPrefs.getBoolean("IS_24_HOUR", false)
        currentScheduleItem=schedule
        titleTextView.text=currentScheduleItem.title
        var hoursFormat=currentScheduleItem.hours
        var timeOfDay = ""
        if(!is24Saved) {
            timeOfDay = "AM"
            if (hoursFormat - 12 > 0) {
                hoursFormat -= 12
                timeOfDay = "PM"
            }
        }
        var minutesFormat=currentScheduleItem.minutes
        var minutesText="${currentScheduleItem.minutes}"
        if(10-minutesFormat>0){
            minutesText="0$minutesText"
        }
        timeTextView.text="${currentScheduleItem.date}; $hoursFormat:$minutesText$timeOfDay"

        deleteButton.setOnClickListener(){
            var position=adapterPosition

        }
    }
}