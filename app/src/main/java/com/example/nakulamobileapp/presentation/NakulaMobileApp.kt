package com.example.nakulamobileapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nakulamobileapp.presentation.navigation.NakulaMobileNavGraph
import kotlinx.coroutines.delay

@Composable
fun NakulaMobileApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    var showSplashScreen by remember { mutableStateOf(true) }

    if (showSplashScreen) {
        SplashScreen()
        LaunchedEffect(key1 = true) {
            delay(2000) // Delay for 2 seconds
            showSplashScreen = false
        }
    } else {
        NakulaMobileNavGraph(navController = navController, modifier = modifier)
    }
}