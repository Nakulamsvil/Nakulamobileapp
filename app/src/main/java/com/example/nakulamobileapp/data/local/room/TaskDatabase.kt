package com.example.nakulamobileapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nakulamobileapp.data.local.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}