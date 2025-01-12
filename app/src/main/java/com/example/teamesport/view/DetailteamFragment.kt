package com.example.teamesport.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentDetailteamBinding
import com.example.teamesport.viewmodel.MemberViewModel
import com.example.teamesport.viewmodel.TeamViewModel
import com.squareup.picasso.Picasso


class DetailteamFragment : Fragment() {
    private lateinit var binding: FragmentDetailteamBinding
    private lateinit var viewModel: MemberViewModel
    private val memberListAdapter = MemberAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailteamBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MemberViewModel::class.java)
        var teamId = arguments?.getInt("teamIds")
        viewModel.refresh(teamId.toString().toInt())
        Log.d("MemberFragment", "teamId : $teamId")
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = memberListAdapter
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
        observeViewModel()
        picasso.build().load(gambar).into(binding.imgGame)
    }

    fun observeViewModel() {
        viewModel.memberLD.observe(viewLifecycleOwner, Observer {
            //TODO salah penamaan
            memberListAdapter.updateStudentList(it)
            Log.d("MemberFragmanet", "observeViewModel: masuk")
//              binding.refreshLayout.isRefreshing = false
        })

        viewModel.memberLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recView.visibility = View.GONE
//                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
//                binding.txtError?.visibility = View.GONE
            }
        })
    }
//TODO lik dao
//TODO change register activity to registerFragment
//TODO fix login
//TODO fix top nav
//TODO make apply team
}