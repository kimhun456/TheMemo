package com.github.kimhun456.memoapplication.presentation.add

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.kimhun456.memoapplication.presentation.composables.editor.ContentEditor
import com.github.kimhun456.memoapplication.presentation.composables.editor.TitleEditor

@Composable
fun AddScreen(
    modifier: Modifier,
    addViewModel: AddViewModel,
    navController: NavController
) {
    Column(modifier = modifier) {
        val title: String by addViewModel.title.observeAsState("")
        val content: String by addViewModel.content.observeAsState("")
        TitleEditor(
            title = title
        ) {
            addViewModel.onTitleChange(it)
        }
        ContentEditor(
            content = content,
            modifier = Modifier.weight(1f, true)
        ) {
            addViewModel.onContentChange(it)
        }
    }
}