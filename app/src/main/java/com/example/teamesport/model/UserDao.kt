package com.example.teamesport.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: Model.User)

    @Query("SELECT * FROM users")
    fun selectAllUser(): List<Model.User>

    @Query("SELECT * FROM users WHERE username= :username")
    fun selectUser(username:String): Model.User

    @Delete
    fun deleteTodo(user:Model.User)

    @Update
    fun update(user: Model.User)
}