package com.falou.avaliacao_tecnica_falou.data.repository

import com.falou.avaliacao_tecnica_falou.data.model.Dictionary
import com.falou.avaliacao_tecnica_falou.service.ResponseAny

interface DictionaryRepository {
    suspend fun getTranslation(lang: String, word: String): ResponseAny<Dictionary>
}