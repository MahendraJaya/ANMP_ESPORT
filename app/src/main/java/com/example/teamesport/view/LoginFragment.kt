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
import com.example.teamesport.R
import com.example.teamesport.databinding.FragmentLoginBinding
import com.example.teamesport.util.SharePref
import com.example.teamesport.viewmodel.UserViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var sharePref: SharePref

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                    val action = LoginFragmentDirections.actionRegisterActivity()
                    Navigation.findNavController(it).navigate(action)
                    Log.d("cek_sudah_login", "sudah login")
                }else{
                    sharePref.clearSession()
                    Log.d("cek_sudah_login", "belum login")
                }
            }
        }
        binding.btnLogin.performClick()

       binding.btnRegister.setOnClickListener {
           val action = LoginFragmentDirections.actionRegisterActivity()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}