package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buildDb
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.teamesport.model.Achievement
import com.example.teamesport.model.Game
import com.example.teamesport.model.Schedule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ScheduleViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val schedLD = MutableLiveData<List<Schedule>>()
    val schedLoadError = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val register = MutableLiveData<String>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
//    fun refresh() {
//        schedLoadError.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://www.jsonkeeper.com/b/HWWF"
//        Log.d("url", url)
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<List<Schedule>>() {}.type
//                val result = Gson().fromJson<List<Schedule>>(it, sType)
//                schedLD.value = result as ArrayList<Schedule>?
//                loadingLD.value = false
//                Log.d("showvolley", result.toString())
//
//                },
//            {
//                Log.d("showvolley", it.toString())
//                schedLoadError.value = false
//                loadingLD.value = false
//            }
//        )
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

    fun refresh() {
        schedLoadError.value = false
        loadingLD.value = true

        launch {
            try {
                // Fetch data from the database
                val db = buildDb(getApplication())
                val cachedGames = db.scheduleDao().getAllSchedules()
                schedLD.postValue(cachedGames)
                loadingLD.postValue(false)
                Log.d("GameViewModel", "berhasil fetching data")
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching data", e)
                schedLoadError.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun inputSched(ids: Int, schedsEvent: String, schedsTeam: String, schedsTime: String, schedsMon: String, schedsDate: String, schedsDesc: String, schedsLocation: String, schedsPhotoUrl: String) {
        launch {
            try {
                val db = buildDb(getApplication())
                val newUser = Schedule(
                    id = ids,
                    schedEvent = schedsEvent,
                    schedTeam = schedsTeam,
                    schedTime = schedsTime,
                    schedMon = schedsMon,
                    schedDate = schedsDate,
                    schedDesc = schedsDesc,
                    schedLocation = schedsLocation,
                    schedPhotoUrl = schedsPhotoUrl

                )
                db.scheduleDao().insertAchievement(newUser)
                register.postValue("berhasil tambah")
                refresh() // Refresh the data after inserting
                Log.d("ScheduleViewModel", "Berhasil")
            } catch (e: Exception) {
                Log.e("ScheduleViewModel", "Error inserting data", e)
                schedLoadError.postValue(true)
            }
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}