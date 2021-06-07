package com.cummings.honorsmobileapp.todolistapp

import android.content.Intent

data class ScheduleItem(var title: String, val hours: Int, val minutes: Int, val date: String, val intent: Intent)
