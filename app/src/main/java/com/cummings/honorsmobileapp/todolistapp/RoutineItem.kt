package com.cummings.honorsmobileapp.todolistapp

import android.content.Intent

data class RoutineItem(var title: String, val hours: Int, val minutes: Int, val intent: Intent)
