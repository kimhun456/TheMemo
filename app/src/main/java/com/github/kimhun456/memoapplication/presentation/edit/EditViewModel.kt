package com.github.kimhun456.memoapplication.presentation.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.domain.interactor.memo.LoadMemoUseCase
import com.github.kimhun456.memoapplication.domain.interactor.memo.RemoveMemoUseCase
import com.github.kimhun456.memoapplication.domain.interactor.memo.UpdateMemoUseCase
import com.github.kimhun456.memoapplication.presentation.constants.NavigationConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val updateMemoUseCase: UpdateMemoUseCase,
    private val loadMemoUseCase: LoadMemoUseCase,
    private val removeMemoUseCase: RemoveMemoUseCase
) : ViewModel() {

    private val _title: MutableLiveData<String> = MutableLiveData<String>()
    val title: LiveData<String> = _title
    private val _content: MutableLiveData<String> = MutableLiveData<String>()
    val content: LiveData<String> = _content

    private var currentMemo: Memo? = null

    init {
        val memoId = savedStateHandle.get<Long>(NavigationConstants.MEMO_ID)
        Timber.i("get memo from $memoId")
        memoId?.let {
            loadMemo(it)
        }
    }

    private fun loadMemo(memoId: Long) {
        loadMemoUseCase.loadMemo(memoId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.i("loadMemo Success $it")
                    currentMemo = it
                    _title.value = it.title
                    _content.value = it.message
                },
                { throwable -> Timber.e(throwable) }
            )
    }

    fun onTitleChange(title: String) {
        _title.value = title
    }

    fun onContentChange(content: String) {
        _content.value = content
    }

    private fun finishMemo() {
        currentMemo?.let { memo ->
            memo.lastModifiedTime = System.currentTimeMillis()
            memo.title = title.value ?: ""
            memo.message = content.value ?: ""
            if (memo.title.isNotBlank() || memo.message.isNotBlank()) {
                updateMemoUseCase.updateMemo(memo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { Timber.i("updateMemo Success") },
                        { throwable -> Timber.e(throwable) }
                    )
            } else {
                removeMemoUseCase.removeMemo(memo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { Timber.i("removeMemo Success") },
                        { throwable -> Timber.e(throwable) }
                    )
            }
        }
    }

    override fun onCleared() {
        finishMemo()
        Timber.d("onCleared()")
        super.onCleared()
    }
}