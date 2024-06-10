package com.example.nakulamobileapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.nakulamobileapp.presentation.home.homeScreenRoute
import com.example.nakulamobileapp.presentation.task.taskScreenRoute

@Composable
fun NakulaMobileNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        homeScreenRoute(navController)
        taskScreenRoute(navController)
    }
}