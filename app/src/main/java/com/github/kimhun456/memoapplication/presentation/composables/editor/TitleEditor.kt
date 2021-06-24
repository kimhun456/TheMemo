package com.github.kimhun456.memoapplication.presentation.composables.editor

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme

@Composable
fun TitleEditor(
    title: String,
    onTitleChange: (String) -> Unit,
) {
    TextField(
        placeholder = {
            Text(text = "Title")
        },
        value = title,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small.copy(),
        onValueChange = onTitleChange,
        textStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
        maxLines = 3,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = MaterialTheme.colors.surface
        )
    )
}

@Preview
@Composable
fun PreviewTitleEditor() {
    TheMemoTheme {
        TitleEditor("Title") { }
    }
}
