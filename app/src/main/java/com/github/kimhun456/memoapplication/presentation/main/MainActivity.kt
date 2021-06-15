package com.github.kimhun456.memoapplication.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMemoTheme {
                MainContent()
            }
        }
    }

    @Composable
    fun MainContent() {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            modifier = Modifier.padding(8.dp),
            scaffoldState = scaffoldState,
            bottomBar = {
                MainBottomAppBar(
                    onClickMenuIcon = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Menu clicked")
                        }
                    },
                    onClickSettingsIcon = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("setting clicked")
                        }
                    },
                    onClickAddIcon = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("plus clicked")
                        }
                    }
                )
            },
            floatingActionButton = {
                WriteButton {
                    viewModel.addMemo()
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Add Generated Memo")
                    }
                }
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
        )
        { innerPadding ->
            MainScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel
            )
        }
    }
}