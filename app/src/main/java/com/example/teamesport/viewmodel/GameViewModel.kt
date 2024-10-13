package com.example.teamesport.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teamesport.model.Model

class GameViewModel: ViewModel(){

    val gameLD = MutableLiveData<ArrayList<Model.Game>>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        gameLD.value = arrayListOf(
            Model.Game("16055","The Legend of Zelda: Breath of the Wild","An open-world adventure game set in a vast and beautiful Hyrule."),
            Model.Game("13312","Assassin's Creed Valhalla", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
            Model.Game("11204","Cyberpunk 2077","Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure ")
        )

        gameLoadErrorLD.value = false
        loadingLD.value = false
    }


}