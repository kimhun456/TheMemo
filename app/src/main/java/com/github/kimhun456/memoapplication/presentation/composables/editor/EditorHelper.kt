package com.github.kimhun456.memoapplication.presentation.composables.editor

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun EditorHelper(
    onUndoClick: () -> Unit = {},
    onRedoClick: () -> Unit = {}
) {
    BottomAppBar(
        cutoutShape = RoundedCornerShape(percent = 50),
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsWithImePadding()
    ) {
        Spacer(Modifier.weight(1f, true))
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

@Preview
@Composable
fun PreviewEditorHelper() {
    TheMemoTheme {
        EditorHelper()
    }
}