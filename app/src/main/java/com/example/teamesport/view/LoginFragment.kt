package com.example.teamesport.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import buildDb
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentLoginBinding
import com.example.teamesport.model.Game
import com.example.teamesport.util.SharePref
import com.example.teamesport.viewmodel.GameViewModel
import com.example.teamesport.viewmodel.UserViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var sharePref: SharePref
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container, false
        )
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sharePref = SharePref(requireContext())
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.cek_db()
        binding.btnLogin.setOnClickListener{

            if(sharePref.isLoggedIn()){
                val action = LoginFragmentDirections.actionItemWhatweplay()
                Navigation.findNavController(it).navigate(action)
//                sharePref.clearSession()
                Log.d("cek_sudah_login", "sahrePref login")
            }else{
                sharePref.clearSession()
                Log.d("cek_sudah_login", "sharePref belum login2")
            }

            if(binding.inputUsername != null && binding.inputPassword != null){
                viewModel.login(binding.inputUsername.text.toString(), binding.inputPassword.text.toString())
            }

            viewModel.login.observe(viewLifecycleOwner){
                    isLoggedin ->
                if (isLoggedin){
                    val action = LoginFragmentDirections.actionItemWhatweplay()
                    Navigation.findNavController(it).navigate(action)
                    Log.d("cek_sudah_login", "sudah login")
                }else{
                    sharePref.clearSession()
                    Log.d("cek_sudah_login", "belum login")
                }
            }
        }
//        binding.btnLogin.performClick()

        binding.btnLogin.setOnClickListener{
//            if(binding.inputUsername != null && binding.inputPassword != null){
//                viewModel.login(binding.inputUsername.text.toString(), binding.inputPassword.text.toString())
//            }
//            viewModel.login.observe(viewLifecycleOwner){
//                    isLoggedin ->
//                if (isLoggedin){
//                    val action = LoginFragmentDirections.actionItemWhatweplay()
//                    Navigation.findNavController(it).navigate(action)
//                    Log.d("cek_sudah_login", "sudah login")
//                }else{
//                    sharePref.clearSession()
//                    Log.d("cek_sudah_login", "belum login")
//                }
//            }
//            val action = LoginFragmentDirections.actionItemWhatweplay()
//            Navigation.findNavController(it).navigate(action)
            val dummy = Game(1,
                "https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg",
                "The Legend of Zelda: Breath of the Wild",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
            gameViewModel.cobaGames(dummy)
            val dummy2 = Game(2,
                "https://upload.wikimedia.org/wikipedia/en/f/ff/Assassin%27s_Creed_Valhalla_cover.jpg",
                "Assassin's Creed Valhalla",
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in ")
            gameViewModel.cobaGames(dummy)
        }

        binding.btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionRegisterActivity()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}