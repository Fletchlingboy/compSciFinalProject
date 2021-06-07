package com.cummings.honorsmobileapp.todolistapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import androidx.fragment.app.Fragment


class SettingsFragment : Fragment() {
    val SHARED_PREFS="sharedPrefs"
    var is24Hour=false
    var allowNotifs=true
    lateinit var hourSwitch: Switch
    lateinit var  notifSwitch: Switch
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView=inflater.inflate(R.layout.fragment_settings, container, false)
        hourSwitch=rootView.findViewById(R.id.switch1)
        notifSwitch=rootView.findViewById(R.id.switch2)
        loadData()
        hourSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
           is24Hour=isChecked
            saveData()
        })
        notifSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            allowNotifs=isChecked
            saveData()
        })
        return rootView
    }
    fun saveData(){
        val sharedPreferences: SharedPreferences=(activity as MainActivity?)!!.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putBoolean("IS_24_HOUR",is24Hour)
            putBoolean("ALLOW_NOTIFS",allowNotifs)
        }.apply()
    }
    fun loadData(){
        val sharedPreferences: SharedPreferences=(activity as MainActivity?)!!.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val is24Saved=sharedPreferences.getBoolean("IS_24_HOUR", false)
        val allowsNotifsSaved=sharedPreferences.getBoolean("ALLOW_NOTIFS",false)
        hourSwitch.isChecked=is24Saved
        notifSwitch.isChecked=allowsNotifsSaved
    }
}