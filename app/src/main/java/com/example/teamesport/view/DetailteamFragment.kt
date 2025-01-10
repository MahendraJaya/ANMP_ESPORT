package com.example.teamesport.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentDetailteamBinding
import com.squareup.picasso.Picasso


class DetailteamFragment : Fragment() {
    private lateinit var binding: FragmentDetailteamBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailteamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtTeam.text = arguments?.getString("team")
        val picasso = Picasso.Builder(view.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        val gambar = arguments?.getString("gambar")
        picasso.build().load(gambar).into(binding.imgGame)
    }
//TODO change all json to DAO
//TODO change register activity to registerFragment
//TODO fix login
//TODO fix top nav
//TODO make apply team
}