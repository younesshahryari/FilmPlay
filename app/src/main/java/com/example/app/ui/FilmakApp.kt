package com.example.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.app.navigation.FilmakNavGraph
import com.example.core.designsystem.component.FilmakBackground
import com.example.core.designsystem.component.FilmakNavigationSuiteScaffold
import com.example.core.designsystem.component.FilmakTopAppBar
import com.example.core.icon.FilmakIcons
import com.example.movie.navigation.MovieDestination
import kotlin.reflect.KClass

@Composable
fun FilmakApp(
    appState: FilmakAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    FilmakBackground(modifier = modifier) {
        val snackBarHostState = remember { SnackbarHostState() }
        FilmakApp(
            appState = appState,
            snackBarHostState = snackBarHostState,
            windowAdaptiveInfo = windowAdaptiveInfo,
        )
    }

}

@Composable
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
)
internal fun FilmakApp(
    appState: FilmakAppState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {

    val currentDestination = appState.currentDestination

    FilmakNavigationSuiteScaffold(
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach { destination ->
                val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)
                item(
                    selected = selected,
                    onClick = { appState.navigateToTopLevelDestination(destination) },
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = null,
                        )
                    },
                    label = { Text(stringResource(destination.tabName)) },
                    modifier = Modifier,
                )
            }
        },
        windowAdaptiveInfo = windowAdaptiveInfo,
    ) {
        Scaffold(
            modifier = modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(snackBarHostState) },
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
                // Show the top app bar on top level destinations.
                val destination = appState.currentTopLevelDestination
                var shouldShowTopAppBar = false

                if (destination != null) {
                    shouldShowTopAppBar = true
                    FilmakTopAppBar(
                        titleRes = destination.toolbarTitle,
                        navigationIcon = FilmakIcons.Search,
                        navigationIconContentDescription = "",
                        actionIcon = FilmakIcons.Settings,
                        actionIconContentDescription = "",
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent,
                        ),
                        onActionClick = { /*onTopAppBarActionClick()*/ },
                        onNavigationClick = {
                            appState.navigationToSearch(destination)
                        },
                    )
                }

                Box(
                    // Workaround for https://issuetracker.google.com/338478720
                    modifier = Modifier.consumeWindowInsets(
                        if (shouldShowTopAppBar) {
                            WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                        } else {
                            WindowInsets(0, 0, 0, 0)
                        },
                    ),
                ) {
                    FilmakNavGraph(
                        appState = appState,
                        startDestination = MovieDestination.BaseRoute,
                    )
                }
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } == true
