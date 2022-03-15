package com.falou.avaliacao_tecnica_falou.domain

import com.falou.avaliacao_tecnica_falou.data.model.Dictionary
import com.falou.avaliacao_tecnica_falou.service.ResponseAny

interface GetDictionaryUseCase {
    suspend operator fun invoke(lang: String, word: String): ResponseAny<Dictionary>
}