package com.example.teamesport.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentScheduleBinding
import com.example.teamesport.viewmodel.GameViewModel
import com.example.teamesport.viewmodel.ScheduleViewModel
import kotlin.math.log


class ScheduleFragment : Fragment() {

    private lateinit var binding:FragmentScheduleBinding
    private lateinit var viewModel: ScheduleViewModel
    private val scheduleListAdapter = ScheduleAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScheduleBinding.inflate(inflater,container,false)
        return binding.root
        Log.d("created", "onCreateView: ")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()
        Log.d("created", "viewmodel ")
        binding.recView.layoutManager = LinearLayoutManager(context)
        Log.d("created", "lm: ")
        binding.recView.adapter = scheduleListAdapter
        Log.d("created", "schedadapt: ")
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.schedLD.observe(viewLifecycleOwner){
            scheduleListAdapter.updateScheduleList(it)
            Log.d("it", it.toString())
        }
        viewModel.schedLoadError.observe(viewLifecycleOwner, Observer {
            Log.d("created", it.toString())
            if (it == true) {
                binding.recView.visibility = View.GONE
            } else {
                binding.recView.visibility = View.VISIBLE
            }
        })
    }
}