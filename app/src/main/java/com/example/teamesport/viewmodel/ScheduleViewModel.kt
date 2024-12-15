package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.teamesport.model.Model
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ScheduleViewModel(application: Application) : AndroidViewModel(application) {
    val schedLD = MutableLiveData<ArrayList<Model.Schedule>>()
    val schedLoadError = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun refresh() {
        schedLoadError.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/HWWF"
        Log.d("url", url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Model.Schedule>>() {}.type
                val result = Gson().fromJson<List<Model.Schedule>>(it, sType)
                schedLD.value = result as ArrayList<Model.Schedule>?
                loadingLD.value = false
                Log.d("showvolley", result.toString())

                },
            {
                Log.d("showvolley", it.toString())
                schedLoadError.value = false
                loadingLD.value = false
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