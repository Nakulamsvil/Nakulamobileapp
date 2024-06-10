package com.example.nakulamobileapp.presentation.home


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nakulamobileapp.presentation.navigation.Screen

fun NavGraphBuilder.homeScreenRoute(navController: NavController) {
    composable(route = Screen.Home.route) {
        HomeScreen(navController)
    }
}