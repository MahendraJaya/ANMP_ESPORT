package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.android.volley.RequestQueue
import com.example.teamesport.model.Achievement
import com.example.teamesport.model.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TeamViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    val teamLD = MutableLiveData<List<Team>>()
    val teamLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val register = MutableLiveData<String>()

    fun refresh(gamesId: Int) {
        teamLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            try {
                // Fetch data from the database
                val db = buildDb(getApplication())
                val cachedAchievements = db.teamDao().getAllTeams(gamesId)
                teamLD.postValue(cachedAchievements)
                loadingLD.postValue(false)
                Log.d("TeamViewModel", "refresh: berhasil fetch data")
            } catch (e: Exception) {
                Log.e("TeamViewModel", "Error fetching data", e)
                teamLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    fun inputTeam(ids: Int, teamsName: String, gamesId: Int) {
        launch {
            try {
                val db = buildDb(getApplication())
                val newUser = Team(
                    id = ids,
                    teamName = teamsName,
                    gameId = gamesId

                )
                db.teamDao().insertTeam(newUser)
                register.postValue("berhasil tambah")
                refresh(gamesId) // Refresh the data after inserting
                Log.d("TeamViewModel", "Berhasil")
            } catch (e: Exception) {
                Log.e("TeamViewModel", "Error inserting data", e)
                teamLoadErrorLD.postValue(true)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}