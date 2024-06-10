package com.example.nakulamobileapp.presentation.home

import com.example.nakulamobileapp.domain.model.Task

data class HomeState(
    val tasks: List<Task> = emptyList()
)
