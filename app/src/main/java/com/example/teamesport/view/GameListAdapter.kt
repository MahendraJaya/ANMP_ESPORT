package com.example.teamesport.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.teamesport.databinding.GameListItemBinding
import com.example.teamesport.model.Game
import com.squareup.picasso.Picasso

class GameListAdapter(val gamelist:ArrayList<Game>):RecyclerView.Adapter<GameListAdapter.WhatWePlayViewHolder>() {

    class WhatWePlayViewHolder(var binding: GameListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatWePlayViewHolder {
        val binding = GameListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WhatWePlayViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gamelist.size
    }

    override fun onBindViewHolder(holder: WhatWePlayViewHolder, position: Int) {
        holder.binding.txtTitle.text = gamelist[position].gameTitle
        holder.binding.txtDescription.text = gamelist[position].gameDescription

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }

        picasso.build().load(gamelist[position].gameImageUrl).into(holder.binding.imageView)

        holder.binding.btnAchievement.setOnClickListener {
            val action = WhatweplayFragmentDirections.actionAchievementFragment(gamelist[position].gameTitle.toString(), gamelist[position].gameImageUrl.toString())
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.btnTeam.setOnClickListener {
            val action = WhatweplayFragmentDirections.actionTeamsFragment(gamelist[position].gameImageUrl.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateStudentList(newStudentList: ArrayList<Game>) {
        gamelist.clear()
        gamelist.addAll(newStudentList)
        notifyDataSetChanged()
    }

}