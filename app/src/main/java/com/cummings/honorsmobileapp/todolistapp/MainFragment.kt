package com.cummings.honorsmobileapp.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.cummings.honorsmobileapp.todolistapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentMainBinding.inflate(inflater,container,false)
        val rootView=binding.root
        binding.toDailyButton.setOnClickListener{v: View->
        rootView.findNavController().navigate(R.id.action_mainFragment_to_routineFragment)
        }
        binding.toOptionsButton.setOnClickListener{v: View->
            //(activity as MainActivity?)!!.buildNotification()
            rootView.findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }
        binding.toCalendarButton.setOnClickListener{v: View->
            rootView.findNavController().navigate(R.id.action_mainFragment_to_calendarScheduleFragment)
        }

        return rootView
    }
}