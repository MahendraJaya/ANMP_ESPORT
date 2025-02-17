package com.example.teamesport.model

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
import java.time.Year
import java.util.Date

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

    data class Schedule(
        @SerializedName("schedEvent")
        var schedEvent: String,
        @SerializedName("schedTeam")
        var schedTeam: String?,
        @SerializedName("schedTime")
        var schedTime: String?,
        @SerializedName("schedMon")
        var schedMon: String?,
        @SerializedName("schedDate")
        var schedDate: String?,
        @SerializedName("schedDesc")
        var schedDesc: String?,
        @SerializedName("schedLocation")
        var schedLocation: String?,
        @SerializedName("schedPhotoUrl")
        var schedPhotoUrl: String?
    )

}