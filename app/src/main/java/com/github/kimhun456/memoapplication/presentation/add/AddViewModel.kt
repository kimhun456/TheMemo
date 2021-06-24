package com.github.kimhun456.memoapplication.presentation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.domain.interactor.memo.CreateEmptyMemoUseCase
import com.github.kimhun456.memoapplication.domain.interactor.memo.LoadMemoUseCase
import com.github.kimhun456.memoapplication.domain.interactor.memo.RemoveMemoUseCase
import com.github.kimhun456.memoapplication.domain.interactor.memo.UpdateMemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val createEmptyMemoUseCase: CreateEmptyMemoUseCase,
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
        createAndLoadMemo()
    }

    private fun createAndLoadMemo() {
        createEmptyMemoUseCase.createEmptyMemo()
            .flatMap { loadMemoUseCase.loadMemo(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.i("createAndLoadMemo Success $it")
                    currentMemo = it
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
            if (isModified()) {
                memo.lastModifiedTime = System.currentTimeMillis()
            }
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

    private fun isModified(): Boolean {
        currentMemo?.let {
            return it.title != title.value || it.message != content.value
        }
        return false
    }

    override fun onCleared() {
        finishMemo()
        Timber.d("onCleared()")
        super.onCleared()
    }
}