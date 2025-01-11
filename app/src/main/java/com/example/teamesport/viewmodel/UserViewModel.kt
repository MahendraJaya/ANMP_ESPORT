package com.example.teamesport.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.example.teamesport.model.Model
import com.example.teamesport.util.SharePref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    val login = MutableLiveData<Boolean>()
    val tes_login = true
    val register = MutableLiveData<String>()
    private val sharePref = SharePref(application.applicationContext)
    fun login(username: String, password: String) {
        launch {
            //fungsi login
            val db = buildDb(getApplication())
            val user = db.userDao().selectUser(username)
            if (user != null && user.password == password) {

                sharePref.saveLoginState(username, user.firstname, user.lastname)
                login.postValue(true)
            } else {
                login.postValue(false)
                sharePref.clearSession()
            }
        }
    }

    fun cek_register(firstname: String, lastname: String, username: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            register.postValue("Passwords do not match!")
            return
        }

        launch {
            val db = buildDb(getApplication())
            Log.d("cek_buat_db", "cek_register: buat db")
            val existingUser = db.userDao().selectUser(username)
            if (existingUser != null) {
                register.postValue("Username already exists!")
            } else {
                val newUser = Model.User(
                    firstname = firstname,
                    lastname = lastname,
                    username = username,
                    password = password
                )
                db.userDao().insertAll(newUser)
                register.postValue("Registration successful!")
            }
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}