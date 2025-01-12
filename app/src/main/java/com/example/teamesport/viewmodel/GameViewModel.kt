package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import buildDb
import com.example.teamesport.model.Game
import com.example.teamesport.model.GameDao
import com.example.teamesport.model.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class GameViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    val gameLD = MutableLiveData<List<Game>>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val register = MutableLiveData<String>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        gameLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            try {
                // Fetch data from the database
                val db = buildDb(getApplication())
                val cachedGames = db.gameDao().getAllGames()
                gameLD.postValue(cachedGames)
                loadingLD.postValue(false)
                Log.d("GameViewModel", "berhasil fetching data")
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching data", e)
                gameLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    // Function to insert new games into the database
    fun insertGames(games: List<Game>) {
        viewModelScope.launch {
            try {
                val db = buildDb(getApplication())
                db.gameDao().insertAll(games)
                refresh() // Refresh the data after inserting
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error inserting data", e)
                gameLoadErrorLD.postValue(true)
            }
        }
    }

    fun inputGames(ids: Int, gameImageUrls: String, gameTitles: String, gameDescriptions: String) {
        launch {
            try {
                val db = buildDb(getApplication())
                val newUser = Game(
                    id = ids,
                    gameImageUrl = gameImageUrls,
                    gameTitle = gameTitles,
                    gameDescription = gameDescriptions
                )
                db.gameDao().insertGame(newUser)
                register.postValue("berhasil tambah")
                refresh() // Refresh the data after inserting
                Log.d("GameViewModel", "Berhasil")
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error inserting data", e)
                gameLoadErrorLD.postValue(true)
            }
        }
    }


}