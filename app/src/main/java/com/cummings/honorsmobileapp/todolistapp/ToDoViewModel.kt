package com.cummings.honorsmobileapp.todolistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel: ViewModel() {
    private val _routineItemJson=MutableLiveData("")
    val routineItemJson: LiveData<String>
        get() = _routineItemJson
    private val _scheduleItemJson=MutableLiveData("")
    val scheduleItemJson: LiveData<String>
        get() = _scheduleItemJson

    fun newRoutineJsonString(newJson: String){
        _routineItemJson.value=newJson
    }
    fun newScheduleJsonString(newJson: String){
        _scheduleItemJson.value=newJson
    }
}