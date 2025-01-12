package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
<<<<<<< Updated upstream
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.teamesport.model.Model
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class GameViewModel(application: Application) : AndroidViewModel(application) {

    val gameLD = MutableLiveData<ArrayList<Model.Game>>()
=======
import androidx.lifecycle.viewModelScope
import buildDb
import com.example.teamesport.model.Game
import com.example.teamesport.model.GameDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class GameViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val gameLD = MutableLiveData<List<Game>>()
>>>>>>> Stashed changes
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null



    fun refresh() {
//        gameLD.value = arrayListOf(
//            Model.Game("16055","The Legend of Zelda: Breath of the Wild","An open-world adventure game set in a vast and beautiful Hyrule."),
//            Model.Game("13312","Assassin's Creed Valhalla", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
//            Model.Game("11204","Cyberpunk 2077","Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure ")
//        )

        gameLoadErrorLD.value = false
        loadingLD.value = true

<<<<<<< Updated upstream
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/3N3Z"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                loadingLD.value = false
                Log.d("showvolley", it)
                val type = object : TypeToken<List<Model.Game>>() {}.type
                val result = Gson().fromJson<List<Model.Game>>(it, type)
                gameLD.value = result as ArrayList<Model.Game>?
                loadingLD.value = false

                Log.d("showvolley", result.toString())
                loadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                gameLoadErrorLD.value = false
                loadingLD.value = false
=======
        viewModelScope.launch {
            try {
                // Fetch data from the database
                val db = buildDb(getApplication())
                val cachedGames = db.gameDao().getAllGames()
                gameLD.postValue(cachedGames)
                loadingLD.postValue(false)
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching data", e)
                gameLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
>>>>>>> Stashed changes
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

<<<<<<< Updated upstream
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }


=======
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

    fun cobaGames(games: Game){
        viewModelScope.launch {
            try {
                val db = buildDb(getApplication())
                db.gameDao().insertGame(games)
                Log.d("insert_data_game", "cobaGames: berhasil")
                refresh() // Refresh the data after inserting
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error inserting data", e)
                gameLoadErrorLD.postValue(true)
            }
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
>>>>>>> Stashed changes
}