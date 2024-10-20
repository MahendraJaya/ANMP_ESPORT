package com.example.teamesport.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.teamesport.databinding.ScheduleItemBinding
import com.example.teamesport.model.Model

class ScheduleAdapter(val schedList:ArrayList<Model.Schedule>): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    class ScheduleViewHolder(var binding: ScheduleItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ScheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return schedList.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.binding.txtEvent.text = schedList[position].schedEvent
        holder.binding.txtTeam.text = schedList[position].schedTeam
        holder.binding.txtDateMon.text = schedList[position].schedMon
        holder.binding.txtDateNum.text = schedList[position].schedDate

        holder.binding.cardView.setOnClickListener{
            val action = ScheduleFragmentDirections.actionScheduleFragmentToScheduleDetailFragment(schedList[position].schedEvent, schedList[position].schedTeam.toString(), schedList[position].schedMon.toString(), schedList[position].schedDate.toString(), schedList[position].schedPhotoUrl.toString(),schedList[position].schedDesc.toString(),schedList[position].schedLocation.toString())
            Navigation.findNavController(it).navigate(action)

        }
    }

    fun updateScheduleList(newScheduleList: ArrayList<Model.Schedule>) {
        schedList.clear()
        schedList.addAll(newScheduleList)
        notifyDataSetChanged()
    }


}