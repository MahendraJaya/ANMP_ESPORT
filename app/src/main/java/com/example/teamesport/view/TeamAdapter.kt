package com.example.teamesport.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.teamesport.databinding.FragmentTeamsBinding
import com.example.teamesport.databinding.GameListItemBinding
import com.example.teamesport.databinding.TeamItemBinding
import com.example.teamesport.model.Game
import com.example.teamesport.model.Team
import com.example.teamesport.view.GameListAdapter.WhatWePlayViewHolder

class TeamAdapter(val teamList:ArrayList<Team>, val gambar:String): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    class TeamViewHolder(val binding: TeamItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.binding.btnTeams.text = teamList[position].teamName
        holder.binding.btnTeams.setOnClickListener {
            val action = TeamsFragmentDirections.actionDetailteamFragment(gambar.toString(), teamList[position].teamName, teamList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateStudentList(newStudentList: List<Team>) {
        teamList.clear()
        teamList.addAll(newStudentList)

        notifyDataSetChanged()
    }

}