package com.falou.avaliacao_tecnica_falou.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
class WordEntity(
    @ColumnInfo(name = "id_word")
    @PrimaryKey
    val idWord: Long,
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "audioFile")
    val audioFile: String,
    @ColumnInfo(name = "phoneticSpelling")
    val phoneticSpelling: String
)