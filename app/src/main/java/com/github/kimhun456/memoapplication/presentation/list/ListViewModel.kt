package com.github.kimhun456.memoapplication.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.domain.interactor.memo.FlowAllMemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    flowAllMemoUseCase: FlowAllMemoUseCase
) : ViewModel() {

    private val _memoList = flowAllMemoUseCase.flowMemos().toLiveData()

    val memoList: LiveData<List<Memo>> = _memoList
}