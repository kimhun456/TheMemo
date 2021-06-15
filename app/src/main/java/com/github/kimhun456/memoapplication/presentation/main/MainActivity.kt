package com.github.kimhun456.memoapplication.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityTheme {
                MainScreen(
                    modifier = Modifier.padding(12.dp),
                    viewModel = viewModel
                )
            }
        }
    }

    @Composable
    fun MainActivityTheme(content: @Composable () -> Unit) {
        TheMemoTheme {
            Surface {
                content()
            }
        }
    }
}