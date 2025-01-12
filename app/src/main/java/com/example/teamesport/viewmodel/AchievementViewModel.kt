package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import buildDb
import com.android.volley.RequestQueue
import com.example.teamesport.model.Achievement

import com.example.teamesport.model.AchievementDao
import com.example.teamesport.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AchievementViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {

    private val job = Job()
    val achievementLD = MutableLiveData<List<Achievement>>()
    val achievementLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val register = MutableLiveData<String>()

    fun refresh(games_title: String) {
        achievementLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            try {
                // Fetch data from the database
                val db = buildDb(getApplication())
                val cachedAchievements = db.achievementDao().getAllAchievements(games_title)
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
    fun insertAchievements(achievements: List<Achievement>) {
        launch {
            try {
                val db = buildDb(getApplication())
                db.achievementDao().insertAll(achievements)
//                refresh() // Refresh the data after inserting
            } catch (e: Exception) {
                Log.e("AchievementViewModel", "Error inserting data", e)
                achievementLoadErrorLD.postValue(true)
            }
        }
    }

    fun inputAchievement(ids: Int, games_title: String, games_achievement: String, games_year: Int) {
        launch {
            try {
                val db = buildDb(getApplication())
                val newUser = Achievement(
                    id = ids,
                    gameTitle = games_title,
                    gameAchievement = games_achievement,
                    gameYear = games_year
                )
                db.achievementDao().insertAchievement(newUser)
                register.postValue("berhasil tambah")
                refresh(games_title) // Refresh the data after inserting
                Log.d("AchievementViewModel", "Berhasil")
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

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}