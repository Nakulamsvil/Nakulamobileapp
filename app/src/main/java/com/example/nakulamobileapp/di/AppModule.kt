package com.example.nakulamobileapp.di

import com.example.nakulamobileapp.domain.usecase.TaskUseCase
import com.example.nakulamobileapp.domain.usecase.TaskUseCaseInteract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindRepository(taskUseCaseInteract: TaskUseCaseInteract): TaskUseCase
}