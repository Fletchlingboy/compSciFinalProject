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
import com.cummings.honorsmobileapp.todolistapp.databinding.FragmentRoutineBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class RoutineFragment : Fragment() {
    private val viewModel: ToDoViewModel by activityViewModels()
    var routineList= mutableListOf<RoutineItem>()
    private var _binding: FragmentRoutineBinding?=null
    private val binding get()=_binding!!
    lateinit var mAdapter: RoutineItemAdapter
    val SHARED_PREFS="sharedPrefs"
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentRoutineBinding.inflate(inflater, container, false)
        val rootView=binding.root
        val mRecyclerView: RecyclerView=binding.recyclerView
        val sharedPreferences: SharedPreferences=(activity as MainActivity?)!!.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        mAdapter=RoutineItemAdapter(routineList, sharedPreferences)

        binding.addNewItem.setOnClickListener(){
            //addItem()
            var main: String="h"
            Log.d(main, "sus")
            (activity as MainActivity?)!!.setRepeatedAlarm()
        }
//        binding.removingStuff.setOnClickListener(){
//
//        }
        mRecyclerView.adapter=mAdapter


        viewModel.routineItemJson.observe(viewLifecycleOwner){newJson->
        var gson= Gson()
        if(newJson!="") {
            val type: Type = object : TypeToken<MutableList<RoutineItem>>() {}.type
            routineList.clear()
            routineList.addAll(gson.fromJson(newJson, type))
        }
            mAdapter.notifyDataSetChanged()
            Log.d("thingy changed", newJson)
        }
        return rootView
    }
}