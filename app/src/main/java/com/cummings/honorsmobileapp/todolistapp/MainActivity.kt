package com.cummings.honorsmobileapp.todolistapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cummings.honorsmobileapp.todolistapp.service.AlarmService
import com.google.gson.Gson
import io.karn.notify.Notify
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    var title=""
    var date=""
    var hours=0
    var minutes=0
    var isRepeated=true
    var routineItemList= mutableListOf<RoutineItem>()
    var scheduleItemList= mutableListOf<ScheduleItem>()
    lateinit var alarmId: Intent


    private val viewModel: ToDoViewModel by viewModels()

    lateinit var alarmService: AlarmService
    override fun onCreate(savedInstanceState: Bundle?) {
        title=""
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alarmService= AlarmService(this)
    }
    fun setExactAlarm(){
        isRepeated=false
        setAlarm{ timeInMillis->alarmService.setExactAlarm(timeInMillis)}
    }
    fun setRepeatedAlarm(){
        isRepeated=true
        setAlarm{alarmService.setRepeatingAlarm(it)}
    }
    fun setAlarm(callback: (Long) -> Unit){
        val builder=AlertDialog.Builder(this)
        val inflater=layoutInflater
        val dialogLayout=inflater.inflate(R.layout.dialog_title,null)
        val editText: EditText=dialogLayout.findViewById(R.id.editText)

        with(builder){
            setPositiveButton("Done"){dialog,which->
                title=editText.text.toString()

        Calendar.getInstance().apply{
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                DatePickerDialog(
                        this@MainActivity,
                        0,
                        { view, year, month, dayOfMonth ->

                            this.set(Calendar.YEAR, year)
                            this.set(Calendar.MONTH, month)
                            this.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                            TimePickerDialog(
                                    this@MainActivity,
                                    { _, hourOfDay, minute ->
                                        this.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                        this.set(Calendar.HOUR_OF_DAY, minute)
                                        dateTextMaker(hourOfDay, minute, timeInMillis)
                                        callback(this.timeInMillis)

                                    },
                                    this.get(Calendar.HOUR_OF_DAY),
                                    this.get(Calendar.MINUTE),
                                    false
                            ).show()
                        },
                        this.get(Calendar.YEAR),
                        this.get(Calendar.MONTH),
                        this.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
        alarmId=alarmService.getIntent()
            }
            setNegativeButton("Cancel"){dialog,which->
                dialog.dismiss()
            }
            setView(dialogLayout)
            show()

        }
    }
    fun dateTextMaker(newHour: Int, newMinute: Int, time: Long){
        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
        date = simpleDateFormat.format(time)
        minutes=newMinute
        hours=newHour
        if(isRepeated) {
            saveRoutineList()
        }
        else{
            saveScheduleList()
        }

    }
    fun routineItemMaker(title: String) {
        val item=RoutineItem(title, hours, minutes, alarmId)
        routineItemList.add(item)
    }
    fun scheduleItemMaker(title: String){
        val item=ScheduleItem(title,hours,minutes,date,alarmId)
        scheduleItemList.add(item)
    }
    fun saveRoutineList(){
        var gson=Gson()
        routineItemMaker(title)
        var jsonString = gson.toJson(routineItemList)
        viewModel.newRoutineJsonString(jsonString)
    }
    fun saveScheduleList(){
        var gson=Gson()
        scheduleItemMaker(title)
        var jsonString = gson.toJson(scheduleItemList)
        viewModel.newScheduleJsonString(jsonString)
    }
    fun buildNotification(){
//        val sharedPreferences: SharedPreferences =context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val allowsNotifsSaved=true
        if(allowsNotifsSaved) {
            Notify
                    .with(this)
                    .content { // this: Payload.Content.Default
                        title = "Test Notif"
                        text = "Test Notif"
                    }
                    .show()
        }
    }
//    fun cancelAlarm(id: Int){
//        var pendingIntent=PendingIntent.getBroadcast(this, id, intent, 0)
//        alarmService.cancelAlarm(pendingIntent)
//    }
}