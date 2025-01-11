package com.example.teamesport.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.teamesport.R
import com.example.teamesport.databinding.ActivityRegisterBinding
import com.example.teamesport.util.SharePref
import com.example.teamesport.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var sharePref: SharePref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.isEnabled = false

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            binding.btnRegister.isEnabled = isChecked // Enable or disable button
        }

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnRegister.setOnClickListener {
            val firstname = binding.inputFirstname.text.toString()
            val lastname = binding.inputLastname.text.toString()
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()
            val confirmPassword = binding.inputConfirm.text.toString()
            viewModel.cek_register(firstname, lastname, username, password, confirmPassword)
        }
        Log.d("tes register", "onCreate: masuk tombol register")
        viewModel.register.observe(this) { status ->
            when {
                status == "Registration successful!" -> {
                Log.d("regsiter_berhasil", "berhasil daftar")
                }
                else -> {
                    Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
}