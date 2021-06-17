package com.github.kimhun456.memoapplication.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.kimhun456.memoapplication.presentation.list.ListViewModel
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private val listViewModel by viewModels<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMemoTheme {
                TheMemoApp(
                    listViewModel = listViewModel,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}