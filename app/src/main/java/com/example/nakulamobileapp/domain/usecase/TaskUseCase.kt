package com.example.nakulamobileapp.domain.usecase

import com.example.nakulamobileapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskUseCase {
    suspend fun upsertTask(task: Task)

    suspend fun deleteTask(taskId: Int)

    fun getTaskById(taskId: Int): Flow<Task?>

    fun getAllTasks(): Flow<List<Task>>
}