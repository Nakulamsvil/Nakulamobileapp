package com.example.nakulamobileapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.MaterialTheme
import com.example.nakulamobileapp.R

@Composable
fun SplashScreen(modifier: Modifier = Modifier,
                 isDarkMode: Boolean = isSystemInDarkTheme()) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val imageResource = if (!isDarkMode) {
            R.drawable.nakulamb_dark // Use light image for dark mode
        } else {
            R.drawable.nakulamb_light // Use dark image for light mode
        }
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Splash Screen Image"
        )
    }
}