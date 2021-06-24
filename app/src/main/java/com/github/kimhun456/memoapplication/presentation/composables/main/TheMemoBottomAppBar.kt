package com.github.kimhun456.memoapplication.presentation.composables.main

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import com.google.accompanist.insets.navigationBarsHeight

@Composable
fun TheMemoBottomAppBar(
    onClickAddIcon: () -> Unit = {}
) {
    Column {
        BottomAppBar(
            cutoutShape = RoundedCornerShape(percent = 50)
        ) {
            Spacer(Modifier.weight(1f, true))
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

@Preview
@Composable
fun PreviewAllListBottomAppBar() {
    TheMemoTheme {
        TheMemoBottomAppBar()
    }
}
