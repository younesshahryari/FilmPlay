package com.aparat.androidinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aparat.androidinterview.persentation.navigation.NavGraph
import com.aparat.androidinterview.persentation.navigation.Route
import com.aparat.androidinterview.ui.theme.AparatAndroidInterviewTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AparatAndroidInterviewTheme {
                val navController = rememberNavController()
                val systemUiColor = rememberSystemUiController()
                systemUiColor.setSystemBarsColor(
                    color = MaterialTheme.colorScheme.secondary,
                )
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {

                    NavGraph(navController, startDestination = Route.HomeRoute)
                }
            }
        }
    }
}
