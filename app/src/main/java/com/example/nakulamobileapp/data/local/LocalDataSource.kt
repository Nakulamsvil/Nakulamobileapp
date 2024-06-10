package com.example.nakulamobileapp.data.local

import com.example.nakulamobileapp.data.local.entity.TaskEntity
import com.example.nakulamobileapp.data.local.room.TaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val taskDao: TaskDao) {

    suspend fun upsertTask(task: TaskEntity) = taskDao.upsertTask(task)

    suspend fun deleteTaskById(id: Int) = taskDao.deleteTaskById(id)

    fun getTaskById(taskId: Int) = taskDao.getTaskById(taskId)

    fun getAllTasks() = taskDao.getAllTasks()
}