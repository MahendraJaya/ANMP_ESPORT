package com.example.teamesport.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentDetailteamBinding
import com.example.teamesport.databinding.FragmentScheduleDetailBinding
import com.squareup.picasso.Picasso


class ScheduleDetailFragment : Fragment() {

    private lateinit var binding: FragmentScheduleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(arguments != null) {
            val schedEvent = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).schedEvent
            val schedMon = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).schedMon
            val schedDate = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).schedDate
            val schedTeam = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).schedTeam
            val schedDesc = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).schedDesc
            val schedLoc = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).schedLoc

            val picasso = Picasso.Builder(view.context)
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }
            val gambar = arguments?.getString("schedPhotoUrl")
            picasso.build().load(gambar).into(binding.imageView4)

            binding.txtViewEvent.text = schedEvent
            binding.txtViewLocTime.text = "$schedLoc, ($schedMon $schedDate)"
            binding.txtTeamName.text = schedTeam
            binding.txtViewDesc.text = schedDesc

            binding.btnNotify.setOnClickListener{
                val action = ScheduleDetailFragmentDirections.actionDialogFragment(schedEvent)
                Navigation.findNavController(it).navigate(action)
            }
        }

    }


}