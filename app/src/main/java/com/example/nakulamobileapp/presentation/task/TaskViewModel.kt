package com.example.nakulamobileapp.presentation.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nakulamobileapp.domain.model.Task
import com.example.nakulamobileapp.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(TaskState())
    val state = _state.asStateFlow()

    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.OnDateChange -> _state.update {
                it.copy(
                    dueDate = event.date
                )
            }

            is TaskEvent.OnDescriptionChange -> _state.update {
                it.copy(
                    description = event.description
                )
            }

            is TaskEvent.OnLocationChange -> _state.update {
                it.copy(
                    location = event.location
                )
            }

            is TaskEvent.OnSourceChange -> _state.update {
                it.copy(
                    source = event.source
                )
            }

            is TaskEvent.OnTitleChange -> _state.update {
                it.copy(
                    title = event.title
                )
            }

            is TaskEvent.OnGetTaskById -> fetchTask(event.id)
        }
    }

    fun saveTask(task: Task) = viewModelScope.launch {
        taskUseCase.upsertTask(
            task = task
        )
    }

    fun deleteTask(taskId: Int) = viewModelScope.launch {
        taskUseCase.deleteTask(taskId)
    }

    private fun fetchTask(taskId: Int) = viewModelScope.launch {
        taskUseCase.getTaskById(taskId).collect { task ->
            _state.update {
                it.copy(
                    currentTaskId = task?.taskId,
                    title = task?.title ?: "",
                    description = task?.description ?: "",
                    location = task?.location ?: "",
                    source = task?.linkSource ?: "",
                    dueDate = task?.dueDate,
                )
            }
        }
    }

    fun isDatePickerDialogOpen() = _state.update {
        it.copy(
            isDatePickerDialogOpen = true
        )
    }

    fun isDatePickerDialogClosed() = _state.update {
        it.copy(
            isDatePickerDialogOpen = false
        )
    }
}