package com.github.kimhun456.memoapplication.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
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
        CreateRandomMemoButton(onClick = viewModel::addMemo)
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
fun CreateRandomMemoButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = "Create Random Memo")
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

@Preview
@Composable
fun PreviewCreateMemoButton() {
    TheMemoTheme {
        CreateRandomMemoButton {

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