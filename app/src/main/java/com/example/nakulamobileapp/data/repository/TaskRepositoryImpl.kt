package com.example.nakulamobileapp.data.repository

import com.example.nakulamobileapp.data.local.LocalDataSource
import com.example.nakulamobileapp.domain.model.Task
import com.example.nakulamobileapp.domain.repository.TaskRepository
import com.example.nakulamobileapp.utils.DataMapper.mapDomainToEntity
import com.example.nakulamobileapp.utils.DataMapper.mapEntitiesToDomain
import com.example.nakulamobileapp.utils.DataMapper.mapEntityToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : TaskRepository {

    override suspend fun upsertTask(task: Task) =
        localDataSource.upsertTask(mapDomainToEntity(task))

    override suspend fun deleteTask(taskId: Int) = localDataSource.deleteTaskById(taskId)

    override fun getTaskById(taskId: Int): Flow<Task?> = localDataSource.getTaskById(taskId).map {
        mapEntityToDomain(it ?: return@map null)
    }

    override fun getAllTasks(): Flow<List<Task>> = localDataSource.getAllTasks().map {
        mapEntitiesToDomain(it)
    }
}