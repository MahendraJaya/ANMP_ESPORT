package com.example.teamesport.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.example.teamesport.model.Model

class AchievementViewModel(application : Application) : AndroidViewModel(application) {

    val achievementLD = MutableLiveData<ArrayList<Model.Achievement>>()
    val achievementLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh(){
    achievementLD.value = arrayListOf(
        Model.Achievement("The Legend of Zelda: Breath of the Wild", "The International 2nd", 2024),
        Model.Achievement( "The Legend of Zelda: Breath of the Wild", "MPL 1st", 2022),
        Model.Achievement( "The Legend of Zelda: Breath of the Wild", "The International 1st", 2019),
        Model.Achievement( "Assassin's Creed Valhalla", "RT Cup 1st", 2021),
        Model.Achievement( "Assassin's Creed Valhalla", "RW Cup 3rd", 2024),
        Model.Achievement( "Cyberpunk 2077", "Rector Cup 3rd", 2022),
        Model.Achievement( "Cyberpunk 2077", "President Cup 1st", 2024)
    )


        achievementLoadErrorLD.value = false
        loadingLD.value = true


    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}