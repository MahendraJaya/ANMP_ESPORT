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
import com.example.teamesport.viewmodel.ScheduleViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
//    private lateinit var gamesModelView: GameViewModel
//    private lateinit var achModelView: AchievementViewModel
//    private lateinit var  schedModelView : ScheduleViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container, false
        )
//        gamesModelView = ViewModelProvider(this).get(GameViewModel::class.java)
//        achModelView = ViewModelProvider(this).get(AchievementViewModel::class.java)
//        schedModelView = ViewModelProvider(this).get(ScheduleViewModel::class.java)
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
//            schedModelView.inputSched(1,
//                "The International 2024 Final",
//                "OG vs. Team Liquid",
//                "14:00",
//                "Aug",
//                "15",
//                "The Upper Bracket Semifinals at The International 2024 pits two titans of Dota 2 against each other: OG and Team Liquid. With both teams having won multiple major tournaments, this clash is set to be a masterclass in strategy, teamwork, and high-level mechanics. Fans are eager to witness OG’s unique, unpredictable playstyle face off against Liquid’s refined, calculated approach. The stakes are high, as the winner will move one step closer to the Aegis of Champions, while the loser will face a challenging road in the lower bracket. Copenhagen will be the battleground for this highly anticipated match.",
//                "Copenhagen, Denmark",
//                "https://smartlaunch.com/wp-content/uploads/2024/08/the-international-2024-copenhagen-7687.jpg")
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}