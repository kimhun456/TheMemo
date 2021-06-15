package com.github.kimhun456.memoapplication.domain.entity

data class Memo(
    val id: Long,
    val title: String,
    val message: String,
    val createdTime: Long,
    val lastModifiedTime: Long
)
