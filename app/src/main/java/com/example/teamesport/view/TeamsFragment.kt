package com.example.teamesport.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamesport.databinding.FragmentTeamsBinding
import com.example.teamesport.viewmodel.GameViewModel
import com.example.teamesport.viewmodel.TeamViewModel
import com.squareup.picasso.Picasso

class TeamsFragment : Fragment() {
    private lateinit var binding:FragmentTeamsBinding
    private lateinit var viewModel: TeamViewModel

    private lateinit var teamListAdapter : TeamAdapter


    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        var gameids = arguments?.getInt("gameIds")
        viewModel.refresh(gameids.toString().toInt())
        var gambars = arguments?.getString("gambar").toString()
        teamListAdapter = TeamAdapter(arrayListOf(), gambars)
        binding.recView.layoutManager= LinearLayoutManager(context)
        binding.recView.adapter = teamListAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picasso = Picasso.Builder(view.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        var gambar = arguments?.getString("gambar").toString()
        picasso.build().load(gambar).into(binding.imageView3)
        Log.d("TeamsFragment", "link gambar: $gambar")
        observeViewModel()
//        binding.btnDetail.setOnClickListener {
//            val action = TeamsFragmentDirections.actionDetailteamFragment(gambar.toString(), "team A")
//            Navigation.findNavController(it).navigate(action)
//        }
//        binding.btnDetail2.setOnClickListener {
//            val action = TeamsFragmentDirections.actionDetailteamFragment(gambar.toString(), "team B")
//            Navigation.findNavController(it).navigate(action)
//        }
//        binding.btnDetail3.setOnClickListener {
//            val action = TeamsFragmentDirections.actionDetailteamFragment(gambar.toString(), "team C")
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    fun observeViewModel(){
        viewModel.teamLD.observe(viewLifecycleOwner, Observer {
            //TODO salah penamaan
            teamListAdapter.updateStudentList(it)
            Log.d("TeamsFragment", "observeViewModel: masuk")
//              binding.refreshLayout.isRefreshing = false
        })

        viewModel.teamLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it == true){
                binding.recView.visibility = View.GONE
//                binding.txtError?.visibility = View.VISIBLE
            }else{
                binding.recView.visibility = View.VISIBLE
//                binding.txtError?.visibility = View.GONE
            }
        })
    }
}