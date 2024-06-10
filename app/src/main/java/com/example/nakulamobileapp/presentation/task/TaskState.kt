package com.example.nakulamobileapp.presentation.task

data class TaskState(
    val title: String = "",
    val description: String = "",
    val source: String = "",
    val location: String = "",
    val isDatePickerDialogOpen: Boolean = false,
    val dueDate: Long? = null,
    val currentTaskId: Int? = null,
)
