package com.example.teamesport.model

import com.google.gson.annotations.SerializedName

class Model {

    data class Game(
        @SerializedName("gameImageUrl")
        var gameImageUrl:String?,
        @SerializedName("gameTitle")
        var gameTitle:String?,
        @SerializedName("gameDescription")
        var gameDescription:String?
    )


}