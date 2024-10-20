package com.example.teamesport.view

import android.view.LayoutInflater
import android.view.ViewGroup
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
    }

    fun updateScheduleList(newScheduleList: ArrayList<Model.Schedule>) {
        schedList.clear()
        schedList.addAll(newScheduleList)
        notifyDataSetChanged()
    }


}