package com.falou.avaliacao_tecnica_falou.data.repository

import com.falou.avaliacao_tecnica_falou.data.model.Dictionary
import com.falou.avaliacao_tecnica_falou.data.remote.OxfordService
import com.falou.avaliacao_tecnica_falou.service.ResponseAny
import com.falou.avaliacao_tecnica_falou.service.safeApiCall


class DictionaryRepositoryImpl(
    private val oxfordService: OxfordService
): DictionaryRepository {
    override suspend fun getTranslation(lang: String, word: String): ResponseAny<Dictionary> {
        return  safeApiCall {
            oxfordService.getTranslation(
                source_lang = lang,
                word_id = word
        ) }
    }

}