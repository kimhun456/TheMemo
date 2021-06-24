package com.github.kimhun456.memoapplication.presentation.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kimhun456.memoapplication.presentation.composables.editor.ContentEditor
import com.github.kimhun456.memoapplication.presentation.composables.editor.EditorHelper
import com.github.kimhun456.memoapplication.presentation.composables.editor.TitleEditor

@Composable
fun EditScreen(
    editViewModel: EditViewModel,
    navController: NavController
) {
    Column() {

        val title: String by editViewModel.title.observeAsState("")
        val content: String by editViewModel.content.observeAsState("")

        TitleEditor(
            title = title
        ) {
            editViewModel.onTitleChange(it)
        }
        ContentEditor(
            content = content,
            modifier = Modifier.weight(1f, true)
        ) {
            editViewModel.onContentChange(it)
        }
        EditorHelper()
    }
}