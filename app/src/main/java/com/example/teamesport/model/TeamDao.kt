package com.example.teamesport.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TeamDao {
    @Insert
    fun insertAll(teams: List<Team>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(vararg teams: Team)


    @Query("SELECT * FROM teams where gameId = :gameId")
    fun getAllTeams( gameId: Int ): List<Team>

}