package com.github.kimhun456.memoapplication.presentation.main

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme

@Composable
fun CreateMemoButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Edit, contentDescription = "Write start")
    }
}

@Preview
@Composable
fun PreviewAddButton() {
    TheMemoTheme {
        CreateMemoButton {

        }
    }
}