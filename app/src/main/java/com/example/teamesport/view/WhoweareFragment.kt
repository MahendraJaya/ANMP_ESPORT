package com.example.teamesport.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentWhatweplayBinding
import com.example.teamesport.databinding.FragmentWhoweareBinding

class WhoweareFragment : Fragment() {

    private lateinit var binding:FragmentWhoweareBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWhoweareBinding.inflate(
            inflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtTitle.text = "Who We Are"
        binding.txtDescription.text = "Siap untuk menyaksikan dominasi sejati di dunia esports? Tim kami, yang terdiri dari para pemain terbaik dan paling berpengalaman, siap mengguncang panggung dunia dengan permainan yang tak tertandingi. Kami tidak hanya bermain, kami menciptakan sejarah."
    }

}