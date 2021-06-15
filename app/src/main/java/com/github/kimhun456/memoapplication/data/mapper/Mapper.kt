package com.github.kimhun456.memoapplication.data.mapper

import com.github.kimhun456.memoapplication.data.entity.MemoEntity
import com.github.kimhun456.memoapplication.domain.entity.Memo

// todo : refactor to interface
object Mapper {

    fun mapToMemoEntity(memo: Memo): MemoEntity {
        return MemoEntity(
            id = memo.id,
            title = memo.title,
            message = memo.message,
            createdTime = memo.createdTime,
            lastModifiedTime = memo.lastModifiedTime
        )
    }

    fun mapToMemo(memoEntity: MemoEntity): Memo {
        return Memo(
            id = memoEntity.id,
            title = memoEntity.title,
            message = memoEntity.message,
            createdTime = memoEntity.createdTime,
            lastModifiedTime = memoEntity.lastModifiedTime
        )
    }
}