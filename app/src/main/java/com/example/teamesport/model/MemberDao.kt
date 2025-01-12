package com.example.teamesport.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemberDao {
    @Insert
    fun insertAll(teams: List<Member>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(vararg members: Member)


    @Query("SELECT * FROM members where teamId = :teamId")
    fun getAllTeams( teamId: Int ): List<Member>
}