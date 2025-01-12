package com.example.teamesport.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamesport.databinding.FragmentWhatweplayBinding
import com.example.teamesport.model.Game
import com.example.teamesport.viewmodel.GameViewModel


class WhatweplayFragment : Fragment() {
    private lateinit var binding:FragmentWhatweplayBinding

    private lateinit var viewModel:GameViewModel
    private val gameListAdapter  = GameListAdapter(arrayListOf())



    override fun onCreateView( inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWhatweplayBinding.inflate(
            inflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
//        viewModel.refresh()
//        binding.recView.layoutManager= LinearLayoutManager(context)
//        binding.recView.adapter = gameListAdapter

//        observeViewModel()

//        binding.btnAchievement.setOnClickListener {
//            val action = WhatweplayFragmentDirections.actionAchievementFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        binding.btnTeam.setOnClickListener {
//            val action = WhatweplayFragmentDirections.actionTeamsFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    fun observeViewModel(){
<<<<<<< Updated upstream
        viewModel.gameLD.observe(viewLifecycleOwner, Observer {
            gameListAdapter.updateStudentList(it)
//            binding.refreshLayout.isRefreshing = false
        })

        viewModel.gameLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it == true){
                binding.recView.visibility = View.GONE
//                binding.txtError?.visibility = View.VISIBLE
            }else{
                binding.recView.visibility = View.VISIBLE
//                binding.txtError?.visibility = View.GONE
            }
        })
=======
//        viewModel.gameLD.observe(viewLifecycleOwner, Observer {
//            //TODO salah penamaan
////            gameListAdapter.updateStudentList(it as ArrayList<Game>)
////              binding.refreshLayout.isRefreshing = false
//        })
//
//        viewModel.gameLoadErrorLD.observe(viewLifecycleOwner, Observer{
//            if(it == true){
//                binding.recView.visibility = View.GONE
////                binding.txtError?.visibility = View.VISIBLE
//            }else{
//                binding.recView.visibility = View.VISIBLE
////                binding.txtError?.visibility = View.GONE
//            }
//        })
>>>>>>> Stashed changes
    }


}