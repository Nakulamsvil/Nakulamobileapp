package com.example.nakulamobileapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.nakulamobileapp.NakulaMobileApp
import com.example.nakulamobileapp.ui.theme.NakulamobileappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NakulamobileappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NakulaMobileApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}