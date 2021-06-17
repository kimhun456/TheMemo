package com.github.kimhun456.memoapplication.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.github.kimhun456.memoapplication.presentation.list.ListScreen
import com.github.kimhun456.memoapplication.presentation.list.ListViewModel
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val listViewModel by viewModels<ListViewModel>()

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
            scaffoldState = scaffoldState,
            topBar = {
                MainTopAppBar()
            },
            bottomBar = {
                MainBottomAppBar(
                    onClickMenuIcon = {
                        showSnackbar(scope, scaffoldState.snackbarHostState, "Menu clicked")
                    },
                    onClickSettingsIcon = {
                        showSnackbar(scope, scaffoldState.snackbarHostState, "setting clicked")
                    },
                    onClickAddIcon = {
                        showSnackbar(scope, scaffoldState.snackbarHostState, "plus clicked")
                    }
                )
            },
            floatingActionButton = {
                WriteButton {
                    mainViewModel.addMemo()
                    showSnackbar(scope, scaffoldState.snackbarHostState, "Add Generated Memo")
                }
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
        )
        { innerPadding ->
            ListScreen(
                modifier = Modifier.padding(innerPadding),
                listViewModel = listViewModel
            )
        }
    }

    private fun showSnackbar(
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        message: String
    ) {
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(message)
        }
    }
}