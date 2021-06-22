package com.github.kimhun456.memoapplication.presentation.add

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

    val title: MutableLiveData<String> = MutableLiveData<String>()
    val content: MutableLiveData<String> = MutableLiveData<String>()

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