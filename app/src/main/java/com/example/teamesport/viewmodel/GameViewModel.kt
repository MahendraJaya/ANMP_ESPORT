package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.teamesport.model.GameDao
import com.example.teamesport.model.Model
import kotlinx.coroutines.launch


class GameViewModel(application: Application, private val gameDao: GameDao) :
    AndroidViewModel(application) {

    val gameLD = MutableLiveData<List<Model.Game>>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        gameLoadErrorLD.value = false
        loadingLD.value = true

        viewModelScope.launch {
            try {
                // Fetch data from the database
                val cachedGames = gameDao.getAllGames()
                gameLD.postValue(cachedGames)
                loadingLD.postValue(false)
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching data", e)
                gameLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    // Function to insert new games into the database
    fun insertGames(games: List<Model.Game>) {
        viewModelScope.launch {
            try {
                gameDao.insertAll(games)
                refresh() // Refresh the data after inserting
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error inserting data", e)
                gameLoadErrorLD.postValue(true)
            }
        }
    }
}