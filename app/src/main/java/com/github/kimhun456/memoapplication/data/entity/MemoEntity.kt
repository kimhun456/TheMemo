package com.github.kimhun456.memoapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo val title: String,
    @ColumnInfo val message: String,
    @ColumnInfo val createdTime: Long,
    @ColumnInfo val lastModifiedTime: Long
)