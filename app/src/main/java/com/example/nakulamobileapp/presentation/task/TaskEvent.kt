package com.example.nakulamobileapp.presentation.task

sealed interface TaskEvent {
    data class OnTitleChange(val title: String): TaskEvent
    data class OnDescriptionChange(val description: String): TaskEvent
    data class OnDateChange(val date: Long?): TaskEvent
    data class OnSourceChange(val source: String): TaskEvent
    data class OnLocationChange(val location: String): TaskEvent
    data class OnGetTaskById(val id: Int): TaskEvent
}