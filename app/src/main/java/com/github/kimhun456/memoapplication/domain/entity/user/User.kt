package com.github.kimhun456.memoapplication.domain.entity.user

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val gender: Gender
)
