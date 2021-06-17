package com.github.kimhun456.memoapplication.presentation.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.kimhun456.memoapplication.R
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme

@Composable
fun MainTopAppBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        }
    )
}

@Composable
fun MainBottomAppBar(
    onClickMenuIcon: () -> Unit = {},
    onClickSettingsIcon: () -> Unit = {},
    onClickAddIcon: () -> Unit = {}
) {
    BottomAppBar(
        cutoutShape = RoundedCornerShape(percent = 50)
    ) {
        IconButton(onClick = onClickMenuIcon) {
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Menu"
            )
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = onClickSettingsIcon) {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Settings"
            )
        }
        IconButton(onClick = onClickAddIcon) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Preview
@Composable
fun PreviewMainTopAppBar() {
    TheMemoTheme {
        TopAppBar {

        }
    }
}

@Preview
@Composable
fun PreviewMainBottomAppBar() {
    TheMemoTheme {
        MainBottomAppBar()
    }
}

@Composable
fun WriteButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Edit, contentDescription = "Write start")
    }
}

@Preview
@Composable
fun PreviewWriteButton() {
    TheMemoTheme {
        WriteButton {

        }
    }
}