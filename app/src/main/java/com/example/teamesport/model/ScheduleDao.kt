package com.example.teamesport.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScheduleDao {
    @Insert
    fun insertAll(schedules: List<Schedule>)

    @Query("SELECT * FROM schedules")
    fun getAllSchedules(): List<Schedule>
}