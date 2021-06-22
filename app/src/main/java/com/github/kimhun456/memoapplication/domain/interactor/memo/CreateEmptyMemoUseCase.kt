package com.github.kimhun456.memoapplication.domain.interactor.memo

import com.github.kimhun456.memoapplication.domain.repository.MemoRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CreateEmptyMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) {
    fun createEmptyMemo(): Single<Long> = memoRepository.createEmptyMemo()
}