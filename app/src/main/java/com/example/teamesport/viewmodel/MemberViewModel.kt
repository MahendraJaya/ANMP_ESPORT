package com.example.teamesport.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.android.volley.RequestQueue
import com.example.teamesport.model.Member
import com.example.teamesport.model.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MemberViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val memberLD = MutableLiveData<List<Member>>()
    val memberLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val register = MutableLiveData<String>()

    fun refresh(teamId: Int) {
        memberLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            try {
                // Fetch data from the database
                val db = buildDb(getApplication())
                val cachedAchievements = db.memberDao().getAllTeams(teamId)
                memberLD.postValue(cachedAchievements)
                loadingLD.postValue(false)
                Log.d("MemberViewModel", "refresh: berhasil fetch data")
            } catch (e: Exception) {
                Log.e("MemberViewModel", "Error fetching data", e)
                memberLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    fun inputMember(ids: Int, names:String, teamId: Int, roles: String, images: String, gamesId: Int) {
        launch {
            try {
                val db = buildDb(getApplication())
                val newUser = Member(
                    id = ids,
                    name = names,
                    teamId = teamId,
                    role = roles,
                    imageUrl = images

                )
                db.memberDao().insertTeam(newUser)
                register.postValue("berhasil tambah")
                refresh(gamesId) // Refresh the data after inserting
                Log.d("MemberViewModel", "Berhasil")
            } catch (e: Exception) {
                Log.e("MemberViewModel", "Error inserting data", e)
                memberLoadErrorLD.postValue(true)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}