package com.github.kimhun456.memoapplication.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MainViewModel
) {
    Column(modifier = modifier.fillMaxSize()) {
        MemoList(viewModel)
    }
}

@Composable
fun MemoList(viewModel: MainViewModel) {
    val memoList by viewModel.memoList.observeAsState(listOf())
    LazyColumn(
        contentPadding = PaddingValues(top = 8.dp)
    ) {
        items(items = memoList) { memo ->
            MemoCard(memo) { viewModel.removeMemo(memo) }
        }
    }
}

@Composable
fun MemoCard(
    memo: Memo,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style =
                        SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(memo.title)
                    }
                }
            )
            Text(text = memo.message)
        }
    }
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

@Preview
@Composable
fun PreviewMemoCard() {
    val memo = Memo(
        id = 0L,
        title = "Memo Title",
        message = "memo Body",
        createdTime = 0L,
        lastModifiedTime = 0L
    )
    TheMemoTheme {
        MemoCard(memo = memo) {}
    }
}