package com.cummings.honorsmobileapp.todolistapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.cummings.honorsmobileapp.todolistapp.service.AlarmService
import io.karn.notify.*
import java.util.*
import java.util.concurrent.TimeUnit

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val timeInMillis=intent?.getLongExtra("EXTRA_EXACT_ALARM_TIME",0L)
        when(intent?.action){
            "ACTION_SET_EXACT_ALARM"->{
                buildNotification(context,"Your Scheduled Alarm Has Gone Off!","")
            }
            "ACTION_SET_REPETITIVE_ALARM"->{
                val cal= Calendar.getInstance().apply{
                    if (timeInMillis != null) {
                        this.timeInMillis=timeInMillis + TimeUnit.DAYS.toMillis(1)
                    }
                }
                AlarmService(context).setRepeatingAlarm(cal.timeInMillis)
                buildNotification(context,"Your Daily Alarm Has Gone Off","")
            }
        }

    }

    private fun buildNotification(context: Context, notifTitle: String, message: String){
        val sharedPreferences: SharedPreferences =context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val allowsNotifsSaved=sharedPreferences.getBoolean("ALLOW_NOTIFS",false)
        if(allowsNotifsSaved) {
            Notify
                .with(context)
                .content { // this: Payload.Content.Default
                    title = notifTitle
                    text = message
                }
                .show()
        }
    }

}