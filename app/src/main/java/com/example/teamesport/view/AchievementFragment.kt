package com.example.teamesport.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentAchievementBinding
import com.example.teamesport.model.Model
import com.example.teamesport.viewmodel.AchievementViewModel
import com.squareup.picasso.Picasso

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

        //untuk gambar
        val picasso = Picasso.Builder(view.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        val gambar = arguments?.getString("gameImage")
        picasso.build().load(gambar).into(binding.imgGame)

        val tahun = arrayOf("All", "2019", "2020", "2021", "2022", "2023", "2024").toMutableList()
        //untuk load achievement
        binding.txtTitle.text = arguments?.getString("gameName")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tahun)
        binding.spinnerAchievement.adapter = adapter
        binding.spinnerAchievement.setSelection(0)

        //untuk dropdown button atau spinner

        binding.spinnerAchievement.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTahun = tahun[position]
                viewModel.achievementLD.observe(viewLifecycleOwner, Observer {
                        lastAchievement -> viewAchievementWithYear(lastAchievement, binding.txtTitle.text.toString(),
                    selectedTahun)
                })

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.txtAchievement.text = "No Achievement Found"
            }
        }
    }

    fun viewAchievement(achievement: List<Model.Achievement>, gameName: String){
        val filteredAchievements = achievement.filter { it.gameTitle == gameName }
        val formattedAchievements = filteredAchievements.mapIndexed { index, achievement ->
            "${index + 1}. ${achievement.gameAchievement} (${achievement.gameYear})"
        }.joinToString("\n")

        binding.txtAchievement.text = formattedAchievements
    }

    fun viewAchievementWithYear(achievement: List<Model.Achievement>, gameName: String, year:String){

        if(year == "All"){

            val filteredAchievements = achievement.filter { it.gameTitle == gameName }
            val formattedAchievements = filteredAchievements.mapIndexed { index, achievement ->
                "${index + 1}. ${achievement.gameAchievement} (${achievement.gameYear})"
            }.joinToString("\n")
            binding.txtAchievement.text = formattedAchievements
        }else{
            val filteredAchievements = achievement.filter { (it.gameTitle == gameName) && (it.gameYear == year.toInt()) }
            val formattedAchievements = filteredAchievements.mapIndexed { index, achievement ->
                "${index + 1}. ${achievement.gameAchievement} (${achievement.gameYear})"
            }.joinToString("\n")
            binding.txtAchievement.text = formattedAchievements
        }



    }
}