package com.github.kimhun456.memoapplication.domain.interactor.memo

import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.domain.repository.MemoRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class FlowAllMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) {
    fun flowMemos(): Flowable<List<Memo>> = memoRepository.loadMemos()
}