package com.example.nakulamobileapp.domain.repository

import com.example.nakulamobileapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun upsertTask(task: Task)

    suspend fun deleteTask(taskId: Int)

    fun getTaskById(taskId: Int): Flow<Task?>

    fun getAllTasks(): Flow<List<Task>>
}