package com.github.kimhun456.memoapplication.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.presentation.TheMemoDestinations
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun TheMemoBottomAppBar(
    currentScreen: TheMemoDestinations,
    onClickAddIcon: () -> Unit = {},
    onUndoClick: () -> Unit = {},
    onRedoClick: () -> Unit = {}
) {
    Column {
        BottomAppBar(
            cutoutShape = RoundedCornerShape(percent = 50),
            modifier =
            if (currentScreen == TheMemoDestinations.ADD_ROUTE)
                Modifier
                    .fillMaxWidth()
                    .navigationBarsWithImePadding()
            else Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.weight(1f, true))
            if (currentScreen == TheMemoDestinations.ALL_LIST_ROUTE) {
                IconButton(onClick = onClickAddIcon) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add"
                    )
                }
            } else if (currentScreen == TheMemoDestinations.ADD_ROUTE) {
                IconButton(
                    onClick = onUndoClick
                ) {
                    Icon(Icons.Filled.Undo, contentDescription = "Undo")
                }
                IconButton(
                    onClick = onRedoClick
                ) {
                    Icon(Icons.Filled.Redo, contentDescription = "Redo")
                }
            }
        }
        if (currentScreen == TheMemoDestinations.ALL_LIST_ROUTE) {
            Spacer(
                Modifier
                    .background(Color.Black.copy(alpha = 0.7f))
                    .navigationBarsHeight() // Match the height of the status bar
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun PreviewAllListBottomAppBar() {
    TheMemoTheme {
        TheMemoBottomAppBar(
            currentScreen = TheMemoDestinations.ALL_LIST_ROUTE
        )
    }
}

@Preview
@Composable
fun PreviewAddBottomAppBar() {
    TheMemoTheme {
        TheMemoBottomAppBar(
            currentScreen = TheMemoDestinations.ADD_ROUTE
        )
    }
}
