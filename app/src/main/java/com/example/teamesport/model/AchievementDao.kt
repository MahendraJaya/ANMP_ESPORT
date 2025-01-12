package com.example.teamesport.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AchievementDao {
    @Insert
    fun insertAll(achievements: List<Achievement>)

    @Query("SELECT * FROM achievements WHERE game_title = :games_title")
    fun getAllAchievements(games_title : String): List<Achievement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAchievement(vararg achievement: Achievement)
}