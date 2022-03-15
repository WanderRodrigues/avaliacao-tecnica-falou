package com.falou.avaliacao_tecnica_falou.db.repository.definition

import com.falou.avaliacao_tecnica_falou.model.Definition

interface DefinitionRepository {
    suspend fun addDefinition(definition: Definition):Long
    suspend fun get(id_word: Long): List<Definition>?
}