package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.persentation.navigation.NavGraph
import com.example.app.ui.persentation.navigation.Route
import com.example.app.ui.theme.FilmPlayTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmPlayTheme {
                val navController = rememberNavController()
                val systemUiColor = rememberSystemUiController()
                systemUiColor.setSystemBarsColor(
                    color = MaterialTheme.colorScheme.background,
                )
                Box(
                    modifier = Modifier
                        .statusBarsPadding()
                        .navigationBarsPadding()
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    NavGraph(navController, startDestination = Route.HomeRoute)
                }
            }
        }
    }
}
