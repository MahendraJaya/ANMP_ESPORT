package com.example.teamesport.model

import DB_NAME
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DB_NAME = "user_database"

@Database(entities = [User::class, Achievement::class, Game::class, Schedule::class, Team::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun achievementDao(): AchievementDao
    abstract fun gameDao(): GameDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun teamDao(): TeamDao


    companion object {
        @Volatile private var instance: UserDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "esport1"
            ).fallbackToDestructiveMigration().build()
    }
}