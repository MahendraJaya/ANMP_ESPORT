package com.example.teamesport.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDao {
    @Insert
    fun insertAll(games: List<Game>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    @Query("SELECT * FROM games")
    fun getAllGames(): List<Game>
}