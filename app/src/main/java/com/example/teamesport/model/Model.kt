package com.example.teamesport.model

import com.google.gson.annotations.SerializedName
import java.time.Year

class Model {

    data class Game(
        @SerializedName("gameImageUrl")
        var gameImageUrl:String?,
        @SerializedName("gameTitle")
        var gameTitle:String?,
        @SerializedName("gameDescription")
        var gameDescription:String?
    )

    data class Achievement(
        @SerializedName("gameTitle")
        var gameTitle:String?,
        @SerializedName("gameAchievement")
        var gameAchievement:String?,
        @SerializedName("gameYear")
        var gameYear: Int?
    )

}