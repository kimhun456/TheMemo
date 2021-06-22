package com.github.kimhun456.memoapplication.domain.entity

data class Memo(
    val id: Long,
    var title: String,
    var message: String,
    val createdTime: Long,
    var lastModifiedTime: Long
)
