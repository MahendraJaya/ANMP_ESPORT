package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
<<<<<<< Updated upstream
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.teamesport.model.Model
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
=======
import com.example.teamesport.model.Achievement

import com.example.teamesport.model.AchievementDao
>>>>>>> Stashed changes

class AchievementViewModel(application : Application) : AndroidViewModel(application) {

<<<<<<< Updated upstream
    val achievementLD = MutableLiveData<ArrayList<Model.Achievement>>()
=======

class AchievementViewModel(application: Application, private val achievementDao: AchievementDao) :
    AndroidViewModel(application) {

    val achievementLD = MutableLiveData<List<Achievement>>()
>>>>>>> Stashed changes
    val achievementLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh(){
        achievementLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/31FE"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                loadingLD.value = false
                Log.d("showvolley", it)
                val type = object : TypeToken<List<Model.Achievement>>() {}.type
                val result = Gson().fromJson<List<Model.Achievement>>(it, type)
                achievementLD.value = result as ArrayList<Model.Achievement>?
                loadingLD.value = false

<<<<<<< Updated upstream
                Log.d("showvolley", result.toString())
                loadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                achievementLoadErrorLD.value = false
                loadingLD.value = false
=======
    // Function to insert new achievements into the database
    fun insertAchievements(achievements: List<Achievement>) {
        viewModelScope.launch {
            try {
                achievementDao.insertAll(achievements)
                refresh() // Refresh the data after inserting
            } catch (e: Exception) {
                Log.e("AchievementViewModel", "Error inserting data", e)
                achievementLoadErrorLD.postValue(true)
>>>>>>> Stashed changes
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}