package com.example.teamesport.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AchievementDao {
    @Insert
    fun insertAll(achievements: List<Achievement>)

    @Query("SELECT * FROM achievements")
    fun getAllAchievements(): List<Achievement>
}