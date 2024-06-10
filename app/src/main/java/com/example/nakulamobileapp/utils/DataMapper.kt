package com.example.nakulamobileapp.utils

import com.example.nakulamobileapp.data.local.entity.TaskEntity
import com.example.nakulamobileapp.domain.model.Task

object DataMapper {
    fun mapEntitiesToDomain(entities: List<TaskEntity>): List<Task> =
        entities.map {
            Task(
                taskId = it.taskId,
                title = it.title,
                description = it.description,
                dueDate = it.dueDate,
                linkSource = it.linkSource,
                location = it.location,
            )
        }

    fun mapDomainToEntity(task: Task) = TaskEntity(
        taskId = task.taskId,
        title = task.title,
        description = task.description,
        dueDate = task.dueDate,
        linkSource = task.linkSource,
        location = task.location,
    )

    fun mapEntityToDomain(taskEntity: TaskEntity) = Task(
        taskId = taskEntity.taskId,
        title = taskEntity.title,
        description = taskEntity.description,
        dueDate = taskEntity.dueDate,
        linkSource = taskEntity.linkSource,
        location = taskEntity.location,
    )
}