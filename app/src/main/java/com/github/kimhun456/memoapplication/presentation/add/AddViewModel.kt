package com.github.kimhun456.memoapplication.presentation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kimhun456.memoapplication.domain.interactor.memo.CreateMemoUseCase
import com.github.kimhun456.memoapplication.domain.interactor.memo.FlowAllMemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val createMemoUseCase: CreateMemoUseCase,
    flowAllMemoUseCase: FlowAllMemoUseCase
) : ViewModel() {

    val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    val _content = MutableLiveData<String>()
    val content: LiveData<String> = _content

    fun addMemo() {
        // createMemoUseCase.createMemo()
        //     .subscribeOn(Schedulers.io())
        //     .observeOn(AndroidSchedulers.mainThread())
        //     .subscribe(
        //         { Timber.i("add random Memo complete") },
        //         { throwable -> Timber.e(throwable) }
        //     )
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared()")
    }
}