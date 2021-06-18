package com.github.kimhun456.memoapplication.presentation.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun AddScreen() {
    Column() {
        val titleState = remember { mutableStateOf(TextFieldValue()) }
        val bodyState = remember { mutableStateOf(TextFieldValue()) }
        TitleEditor(titleState = titleState)
        ContentEditor(bodyState = bodyState, modifier = Modifier.weight(1f, true))
        EditorHelper()
    }
}

@Composable
fun TitleEditor(titleState: MutableState<TextFieldValue>) {
    TextField(
        placeholder = {
            Text(text = "Title")
        },
        value = titleState.value,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small.copy(),
        onValueChange = { titleState.value = it },
        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
        maxLines = 3,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = MaterialTheme.colors.surface
        )
    )
}

@Composable
fun ContentEditor(
    bodyState: MutableState<TextFieldValue>,
    modifier: Modifier
) {
    TextField(
        placeholder = {
            Text(text = "Content")
        },
        value = bodyState.value,
        modifier = modifier.fillMaxWidth(),
        onValueChange = { bodyState.value = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = MaterialTheme.colors.surface
        )
    )
}

@Composable
fun EditorHelper(
    onUndoClick: () -> Unit = {},
    onRedoClick: () -> Unit = {}
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsWithImePadding()
    ) {
        Spacer(modifier = Modifier.weight(1f, true))
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
fun PreviewTitleEditor() {
    TheMemoTheme {
        val titleState = remember { mutableStateOf(TextFieldValue()) }
        TitleEditor(titleState)
    }
}

@Preview
@Composable
fun PreviewContentEditor() {
    TheMemoTheme {
        val bodyState = remember { mutableStateOf(TextFieldValue()) }
        ContentEditor(bodyState, Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun PreviewEditorHelper() {
    TheMemoTheme {
        EditorHelper()
    }
}