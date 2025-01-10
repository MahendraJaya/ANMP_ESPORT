package com.example.teamesport.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Year
import java.util.Date

class Model {

    @Entity(tableName = "games")
    data class Game(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        @ColumnInfo(name = "game_image_url")
        @SerializedName("gameImageUrl")
        var gameImageUrl: String?,
        @ColumnInfo(name = "game_title")
        @SerializedName("gameTitle")
        var gameTitle: String?,
        @ColumnInfo(name = "game_description")
        @SerializedName("gameDescription")
        var gameDescription: String?
    )

    @Entity(tableName = "achievements")
    data class Achievement(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        @ColumnInfo(name = "game_title")
        @SerializedName("gameTitle")
        var gameTitle: String?,
        @ColumnInfo(name = "game_achievement")
        @SerializedName("gameAchievement")
        var gameAchievement: String?,
        @ColumnInfo(name = "game_year")
        @SerializedName("gameYear")
        var gameYear: Int?
    )

    @Entity(tableName = "schedules")
    data class Schedule(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        @ColumnInfo(name = "sched_event")
        @SerializedName("schedEvent")
        var schedEvent: String,
        @ColumnInfo(name = "sched_team")
        @SerializedName("schedTeam")
        var schedTeam: String?,
        @ColumnInfo(name = "sched_time")
        @SerializedName("schedTime")
        var schedTime: String?,
        @ColumnInfo(name = "sched_mon")
        @SerializedName("schedMon")
        var schedMon: String?,
        @ColumnInfo(name = "sched_date")
        @SerializedName("schedDate")
        var schedDate: String?,
        @ColumnInfo(name = "sched_desc")
        @SerializedName("schedDesc")
        var schedDesc: String?,
        @ColumnInfo(name = "sched_location")
        @SerializedName("schedLocation")
        var schedLocation: String?,
        @ColumnInfo(name = "sched_photo_url")
        @SerializedName("schedPhotoUrl")
        var schedPhotoUrl: String?
    )

    @Entity(tableName = "users")
    data class User(
        @ColumnInfo(name="firstname")
        var firstname: String,
        @ColumnInfo(name="lastname")
        var lastname: String,
        @PrimaryKey var username: String,
        var password: String
    ): Serializable
}