package com.example.university_schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.university_schedule.databinding.FragmentListDayOfWeekBinding
import com.example.university_schedule.databinding.FragmentMainBinding

class ListDayOfWeekFragment : Fragment() {

    private lateinit var binding: FragmentListDayOfWeekBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListDayOfWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mondayDay.setOnClickListener {
            findNavController().navigate(R.id.action_listDayOfWeekFragment_to_mondayFragment)
        }
        binding.thuesdayDay.setOnClickListener {
            findNavController().navigate(R.id.action_listDayOfWeekFragment_to_thuesdayFragment)
        }
        binding.weednesDay.setOnClickListener {
            findNavController().navigate(R.id.action_listDayOfWeekFragment_to_wednesdayFragment)
        }
        binding.thursday.setOnClickListener {
            findNavController().navigate(R.id.action_listDayOfWeekFragment_to_thursdayFragment)
        }
        binding.fridayDay.setOnClickListener {
            findNavController().navigate(R.id.action_listDayOfWeekFragment_to_fridayFragment)
        }
    }
}