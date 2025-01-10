package com.example.teamesport.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentApplyTeamBinding
import com.example.teamesport.viewmodel.ApplyTeamViewModel

class ApplyTeamFragment : Fragment() {

    private lateinit var viewModel: ApplyTeamViewModel
    private lateinit var binding: FragmentApplyTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentApplyTeamBinding.inflate(inflater,container,false)
        return binding.root
    }


}