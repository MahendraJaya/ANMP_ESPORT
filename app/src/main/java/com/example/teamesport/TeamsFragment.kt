package com.example.teamesport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.teamesport.databinding.FragmentTeamsBinding

class TeamsFragment : Fragment() {
    private lateinit var binding:FragmentTeamsBinding
    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDetail.setOnClickListener {
            val action = WhatweplayFragmentDirections.actionAchievementFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}