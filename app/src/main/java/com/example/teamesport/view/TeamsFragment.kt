package com.example.teamesport.view

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

        val gambar = arguments?.getString("gambar")
        binding.btnDetail.setOnClickListener {
            val action = TeamsFragmentDirections.actionDetailteamFragment(gambar.toString(), "team A")
            Navigation.findNavController(it).navigate(action)
        }
        binding.btnDetail2.setOnClickListener {
            val action = TeamsFragmentDirections.actionDetailteamFragment(gambar.toString(), "team B")
            Navigation.findNavController(it).navigate(action)
        }
        binding.btnDetail3.setOnClickListener {
            val action = TeamsFragmentDirections.actionDetailteamFragment(gambar.toString(), "team C")
            Navigation.findNavController(it).navigate(action)
        }
    }

}