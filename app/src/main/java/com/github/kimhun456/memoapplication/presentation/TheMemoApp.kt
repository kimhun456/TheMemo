package com.github.kimhun456.memoapplication.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.kimhun456.memoapplication.presentation.composables.main.CreateMemoButton
import com.github.kimhun456.memoapplication.presentation.composables.main.TheMemoBottomAppBar
import com.github.kimhun456.memoapplication.presentation.composables.main.TheMemoTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TheMemoApp(
    mainViewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = TheMemoDestinations.fromRoute(backstackEntry.value?.destination?.route)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TheMemoTopAppBar(
                currentScreen = currentScreen,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            TheMemoBottomAppBar(
                currentScreen = currentScreen,
                onClickAddIcon = {
                    mainViewModel.addMemo()
                    showSnackbar(
                        scope,
                        scaffoldState.snackbarHostState,
                        "Add Generated Memo"
                    )
                }
            )
        },
        floatingActionButton = {
            if (currentScreen == TheMemoDestinations.ALL_LIST_ROUTE) {
                CreateMemoButton {
                    navController.navigate(route = TheMemoDestinations.ADD_ROUTE.name) {
                        launchSingleTop = true
                    }
                }
            }
        },
        isFloatingActionButtonDocked = currentScreen == TheMemoDestinations.ALL_LIST_ROUTE,
        floatingActionButtonPosition = FabPosition.Center,
    )
    {
        NavGraph(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}

private fun showSnackbar(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    message: String
) {
    scope.launch {
        Timber.d("showSnackbar : $message")
        snackbarHostState.currentSnackbarData?.dismiss()
        snackbarHostState.showSnackbar(message)
    }
}