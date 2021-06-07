package com.cummings.honorsmobileapp.todolistapp.service

import android.content.Context
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import com.cummings.honorsmobileapp.todolistapp.AlarmReceiver
import com.cummings.honorsmobileapp.todolistapp.RandomIntUtil

const val ACTION_SET_EXACT_ALARM="ACTION_SET_EXACT_ALARM"
const val ACTION_SET_REPETITIVE_ALARM="ACTION_SET_REPETITIVE_ALARM"
const val EXTRA_EXACT_ALARM_TIME="EXTRA_EXACT_ALARM_TIME"

class AlarmService(private val context: Context) {
    private val alarmManager: AlarmManager?=
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
    //Sets alarm on specific date
    fun setExactAlarm(timeInMillis: Long){

        setAlarm(
                timeInMillis,
                getPendingIntent(
                        getIntent().apply{
                            action= ACTION_SET_EXACT_ALARM
                            putExtra(EXTRA_EXACT_ALARM_TIME, timeInMillis)
                        }
                )
        )
    }
    //Sets alarm every day
    fun setRepeatingAlarm(timeInMillis: Long){
        setAlarm(
                timeInMillis,
                getPendingIntent(
                        getIntent().apply{
                            action= ACTION_SET_REPETITIVE_ALARM
                            putExtra(EXTRA_EXACT_ALARM_TIME, timeInMillis)

                        }
                )
        )
    }

    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent){
        alarmManager?.let{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        timeInMillis,
                        pendingIntent
                )
            }
            else{
                alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        timeInMillis,
                        pendingIntent
                )
            }
        }
    }
    fun cancelAlarm(pendingIntent: PendingIntent){
        alarmManager?.cancel(pendingIntent)
    }
    fun getIntent()= Intent(context, AlarmReceiver::class.java)

    private fun getPendingIntent(intent: Intent)=
            PendingIntent.getBroadcast(
                    context,
                    RandomIntUtil.getRandomInt(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
}