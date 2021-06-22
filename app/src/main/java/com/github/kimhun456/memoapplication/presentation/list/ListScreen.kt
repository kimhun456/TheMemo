package com.github.kimhun456.memoapplication.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.presentation.theme.TheMemoTheme

@Composable
fun ListScreen(
    modifier: Modifier,
    listViewModel: ListViewModel,
    navController: NavController
) {
    Column(modifier = modifier.fillMaxSize()) {
        MemoList(listViewModel)
    }
}

@Composable
fun MemoList(listViewModel: ListViewModel) {
    val memoList by listViewModel.memoList.observeAsState(listOf())
    LazyColumn(
        contentPadding = PaddingValues(top = 8.dp)
    ) {
        items(items = memoList) { memo ->
            MemoCard(memo) {
                // TODO : go to memo
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemoCard(
    memo: Memo,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 4.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = memo.title, fontWeight = FontWeight.Bold)
            Text(text = memo.message)
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