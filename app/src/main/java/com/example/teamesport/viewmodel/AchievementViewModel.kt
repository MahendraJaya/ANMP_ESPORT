package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.volley.RequestQueue

import com.example.teamesport.model.AchievementDao
import com.example.teamesport.model.Model

import kotlinx.coroutines.launch


class AchievementViewModel(application: Application, private val achievementDao: AchievementDao) :
    AndroidViewModel(application) {

    val achievementLD = MutableLiveData<List<Model.Achievement>>()
    val achievementLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        achievementLoadErrorLD.value = false
        loadingLD.value = true

        viewModelScope.launch {
            try {
                // Fetch data from the database
                val cachedAchievements = achievementDao.getAllAchievements()
                achievementLD.postValue(cachedAchievements)
                loadingLD.postValue(false)
            } catch (e: Exception) {
                Log.e("AchievementViewModel", "Error fetching data", e)
                achievementLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    // Function to insert new achievements into the database
    fun insertAchievements(achievements: List<Model.Achievement>) {
        viewModelScope.launch {
            try {
                achievementDao.insertAll(achievements)
                refresh() // Refresh the data after inserting
            } catch (e: Exception) {
                Log.e("AchievementViewModel", "Error inserting data", e)
                achievementLoadErrorLD.postValue(true)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}