package com.github.kimhun456.memoapplication.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.kimhun456.memoapplication.R
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsHeight
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
                    navController.navigate(TheMemoDestinations.ALL_LIST_ROUTE.name)
                }
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = currentScreen == TheMemoDestinations.ALL_LIST_ROUTE,
                enter = slideInVertically(
                    initialOffsetY = { it }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it }
                )
            ) {
                TheMemoBottomAppBar(
                    onClickMenuIcon = {
                        showSnackbar(scope, scaffoldState.snackbarHostState, "Menu clicked")
                    },
                    onClickSettingsIcon = {
                        showSnackbar(scope, scaffoldState.snackbarHostState, "setting clicked")
                    },
                    onClickAddIcon = {
                        mainViewModel.addMemo()
                        showSnackbar(
                            scope,
                            scaffoldState.snackbarHostState,
                            "Add Generated Memo"
                        )
                    }
                )
            }
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = currentScreen == TheMemoDestinations.ALL_LIST_ROUTE,
                enter = slideInVertically(
                    initialOffsetY = { it }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it * 2 }
                )
            ) {
                AddButton {
                    navController.navigate(
                        route = TheMemoDestinations.ADD_ROUTE.name
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
    )
    {
        NavGraph(navController = navController)
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TheMemoTopAppBar(
    currentScreen: TheMemoDestinations,
    onMenuClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Column {
        Spacer(
            Modifier
                .background(color = MaterialTheme.colors.primarySurface.copy(alpha = 0.7f))
                .statusBarsHeight() // Match the height of the status bar
                .fillMaxWidth()
        )
        TopAppBar(
            navigationIcon = {
                Crossfade(targetState = currentScreen) { screen ->
                    when (screen) {
                        TheMemoDestinations.ADD_ROUTE ->
                            IconButton(
                                onClick = onBackClick
                            ) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                            }
                        TheMemoDestinations.ALL_LIST_ROUTE, TheMemoDestinations.EDIT_ROUTE ->
                            IconButton(
                                onClick = onMenuClick
                            ) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                    }
                }
            },
            title = {
                when (currentScreen) {
                    TheMemoDestinations.ALL_LIST_ROUTE, TheMemoDestinations.EDIT_ROUTE ->
                        Text(text = stringResource(id = R.string.app_name))
                    TheMemoDestinations.ADD_ROUTE -> {
                        // Not used
                    }
                }
            },
            actions = {
                AnimatedVisibility(
                    visible = currentScreen == TheMemoDestinations.ALL_LIST_ROUTE
                ) {
                    IconButton(
                        onClick = onDeleteClick
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            }
        )
    }
}

@Composable
fun TheMemoBottomAppBar(
    onClickMenuIcon: () -> Unit = {},
    onClickSettingsIcon: () -> Unit = {},
    onClickAddIcon: () -> Unit = {}
) {
    Column {
        BottomAppBar(
            cutoutShape = RoundedCornerShape(percent = 50)
        ) {
            IconButton(onClick = onClickMenuIcon) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
            Spacer(Modifier.weight(1f, true))
            IconButton(onClick = onClickSettingsIcon) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }
            IconButton(onClick = onClickAddIcon) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        }
        Spacer(
            Modifier
                .background(Color.Black.copy(alpha = 0.7f))
                .navigationBarsHeight() // Match the height of the status bar
                .fillMaxWidth()
        )
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Edit, contentDescription = "Write start")
    }
}

@Preview
@Composable
fun PreviewTheMemoTopAppBar() {
    TheMemoTheme {
        TheMemoTopAppBar(currentScreen = TheMemoDestinations.ALL_LIST_ROUTE)
    }
}

@Preview
@Composable
fun PreviewTheMemoBottomAppBar() {
    TheMemoTheme {
        TheMemoBottomAppBar()
    }
}

@Preview
@Composable
fun PreviewAddButton() {
    TheMemoTheme {
        AddButton {

        }
    }
}