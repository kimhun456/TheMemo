package com.github.kimhun456.memoapplication.presentation.composables.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.R
import com.github.kimhun456.memoapplication.presentation.TheMemoDestinations
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import com.google.accompanist.insets.statusBarsHeight

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
                        TheMemoDestinations.ADD_ROUTE, TheMemoDestinations.EDIT_ROUTE ->
                            IconButton(
                                onClick = onBackClick
                            ) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                            }
                        TheMemoDestinations.ALL_LIST_ROUTE ->
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

@Preview
@Composable
fun PreviewTheMemoTopAppBar() {
    TheMemoTheme {
        TheMemoTopAppBar(currentScreen = TheMemoDestinations.ALL_LIST_ROUTE)
    }
}