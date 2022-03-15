package com.falou.avaliacao_tecnica_falou.db.repository.word

import com.falou.avaliacao_tecnica_falou.model.Word

interface WordRepository {
    suspend fun add(word: Word):Long
    suspend fun get(word: String): Word?
}