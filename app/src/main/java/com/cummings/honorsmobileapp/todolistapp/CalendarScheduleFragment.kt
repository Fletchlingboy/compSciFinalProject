package com.cummings.honorsmobileapp.todolistapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.cummings.honorsmobileapp.todolistapp.databinding.FragmentCalendarScheduleBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CalendarScheduleFragment : Fragment() {
    private val viewModel: ToDoViewModel by activityViewModels()
    var calendarList= mutableListOf<ScheduleItem>()
    private var _binding: FragmentCalendarScheduleBinding?=null
    private val binding get()=_binding!!
    lateinit var mAdapter: ScheduleItemAdapter
    val SHARED_PREFS="sharedPrefs"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCalendarScheduleBinding.inflate(inflater, container, false)
        val rootView=binding.root
        val mRecyclerView: RecyclerView =binding.recyclerView
        val sharedPreferences: SharedPreferences =(activity as MainActivity?)!!.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        mAdapter=ScheduleItemAdapter(calendarList, sharedPreferences)

        binding.addNewItem.setOnClickListener(){
            (activity as MainActivity?)!!.setExactAlarm()
        }
        mRecyclerView.adapter=mAdapter


        viewModel.scheduleItemJson.observe(viewLifecycleOwner){newJson->
            var gson= Gson()
            if(newJson!="") {
                val type: Type = object : TypeToken<MutableList<ScheduleItem>>() {}.type
                calendarList.clear()
                calendarList.addAll(gson.fromJson(newJson, type))
            }
            mAdapter.notifyDataSetChanged()
        }
        return rootView
    }
}