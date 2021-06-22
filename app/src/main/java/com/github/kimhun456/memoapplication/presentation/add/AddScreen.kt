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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun AddScreen(
    modifier: Modifier,
    addViewModel: AddViewModel,
    navController: NavController
) {
    Column {
        val titleState =
            remember { mutableStateOf(TextFieldValue(text = addViewModel.title.value ?: "")) }
        val contentState =
            remember { mutableStateOf(TextFieldValue(text = addViewModel.content.value ?: "")) }
        TitleEditor(
            titleState = titleState,
            addViewModel = addViewModel
        )
        ContentEditor(
            contentState = contentState,
            addViewModel = addViewModel,
            modifier = Modifier.weight(1f, true)
        )
        EditorHelper()
    }
}

@Composable
fun TitleEditor(
    titleState: MutableState<TextFieldValue>,
    addViewModel: AddViewModel = hiltViewModel()
) {
    TextField(
        placeholder = {
            Text(text = "Title")
        },
        value = titleState.value,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small.copy(),
        onValueChange = {
            titleState.value = it
            addViewModel.title.value = it.text
        },
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
    contentState: MutableState<TextFieldValue>,
    addViewModel: AddViewModel = hiltViewModel(),
    modifier: Modifier
) {
    TextField(
        placeholder = {
            Text(text = "Content")
        },
        value = contentState.value,
        modifier = modifier.fillMaxWidth(),
        onValueChange = {
            contentState.value = it
            addViewModel.content.value = it.text
        },
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
        ContentEditor(bodyState, modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun PreviewEditorHelper() {
    TheMemoTheme {
        EditorHelper()
    }
}