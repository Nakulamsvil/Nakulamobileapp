package com.example.nakulamobileapp.domain.usecase

import com.example.nakulamobileapp.domain.model.Task
import com.example.nakulamobileapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskUseCaseInteract @Inject constructor(
    private val taskRepository: TaskRepository
) : TaskUseCase {
    override suspend fun upsertTask(task: Task) = taskRepository.upsertTask(task)

    override suspend fun deleteTask(taskId: Int) = taskRepository.deleteTask(taskId)

    override fun getTaskById(taskId: Int): Flow<Task?> = taskRepository.getTaskById(taskId)

    override fun getAllTasks(): Flow<List<Task>> = taskRepository.getAllTasks()
}