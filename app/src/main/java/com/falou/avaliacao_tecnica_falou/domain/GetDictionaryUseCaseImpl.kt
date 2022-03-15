package com.falou.avaliacao_tecnica_falou.domain

import com.falou.avaliacao_tecnica_falou.data.model.Dictionary
import com.falou.avaliacao_tecnica_falou.data.repository.DictionaryRepository
import com.falou.avaliacao_tecnica_falou.service.ResponseAny

class GetDictionaryUseCaseImpl(
    private val dictionaryRepository: DictionaryRepository
): GetDictionaryUseCase {
    override suspend fun invoke(lang: String, word: String): ResponseAny<Dictionary> {
        return dictionaryRepository.getTranslation(lang, word)
    }
}