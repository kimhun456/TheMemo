package com.github.kimhun456.memoapplication.presentation.composables.editor

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme

@Composable
fun ContentEditor(
    modifier: Modifier,
    content: String,
    onContentChange: (String) -> Unit
) {
    TextField(
        placeholder = {
            Text(text = "Content")
        },
        value = content,
        modifier = modifier.fillMaxWidth(),
        onValueChange = onContentChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = MaterialTheme.colors.surface
        )
    )
}

@Preview
@Composable
fun PreviewContentEditor() {
    TheMemoTheme {
        ContentEditor(
            modifier = Modifier.fillMaxWidth(),
            content = "This is content section"
        ) { }
    }
}