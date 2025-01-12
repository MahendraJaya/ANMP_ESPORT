package com.example.teamesport.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamesport.databinding.MemberItemBinding
import com.example.teamesport.databinding.TeamItemBinding
import com.example.teamesport.model.Member
import com.example.teamesport.model.Team
import com.example.teamesport.view.TeamAdapter.TeamViewHolder
import com.squareup.picasso.Picasso

class MemberAdapter(val memberList:ArrayList<Member>): RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    class MemberViewHolder(val binding: MemberItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = MemberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.binding.txtName.text = memberList[position].name
        holder.binding.txtRole.text = memberList[position].role

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }

        picasso.build().load(memberList[position].imageUrl).into(holder.binding.imgMem)

    }
    fun updateStudentList(newStudentList: List<Member>) {
        memberList.clear()
        memberList.addAll(newStudentList)

        notifyDataSetChanged()
    }
}