package com.example.teamesport.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentLoginBinding
import com.example.teamesport.model.Achievement
import com.example.teamesport.model.Game
import com.example.teamesport.viewmodel.AchievementViewModel
import com.example.teamesport.viewmodel.GameViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var gamesModelView: GameViewModel
    private lateinit var achModelView: AchievementViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container, false
        )
        gamesModelView = ViewModelProvider(this).get(GameViewModel::class.java)
        achModelView = ViewModelProvider(this).get(AchievementViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener{
            val action = LoginFragmentDirections.actionItemWhatweplay()
            Navigation.findNavController(it).navigate(action)
//            val dumm1 = Game()

//            gamesModelView.inputGames(1, "https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg",
//                "The Legend of Zelda: Breath of the Wild",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
//            achModelView.inputAchievement(1,
//                "The Legend of Zelda: Breath of the Wild",
//                "The International 2023 1st place",
//                2023)

        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}