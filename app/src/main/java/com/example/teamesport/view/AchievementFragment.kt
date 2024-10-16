package com.example.teamesport.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentAchievementBinding
import com.example.teamesport.model.Model
import com.example.teamesport.viewmodel.AchievementViewModel

class AchievementFragment : Fragment() {

    private lateinit var viewModel : AchievementViewModel
    private lateinit var binding : FragmentAchievementBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AchievementViewModel::class.java)
        viewModel.refresh()

        binding.txtTitle.text = arguments?.getString("gameName")
        viewModel.achievementLD.observe(viewLifecycleOwner, Observer {
            lastAchievement -> viewAchievement(lastAchievement, binding.txtTitle.text.toString())
        })
        val tahun = arrayOf("2019", "2020", "2021", "2022", "2023", "2024").toMutableList()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tahun)
        binding.spinnerAchievement.adapter = adapter


    }

    fun viewAchievement(achievement: List<Model.Achievement>, gameName: String){
        val filteredAchievements = achievement.filter { it.gameTitle == gameName }
        val formattedAchievements = filteredAchievements.mapIndexed { index, achievement ->
            "${index + 1}. ${achievement.gameAchievement} (${achievement.gameYear})"
        }.joinToString("\n")

        binding.txtAchievement.text = formattedAchievements
    }

}