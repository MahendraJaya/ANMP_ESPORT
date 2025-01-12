package com.example.teamesport.model

<<<<<<< Updated upstream
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
import java.time.Year
import java.util.Date
=======
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
>>>>>>> Stashed changes

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

<<<<<<< Updated upstream
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
=======
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

    @PrimaryKey
    var username: String,

    var password: String
): Serializable
>>>>>>> Stashed changes
