package com.falou.avaliacao_tecnica_falou.db.repository.example

import com.falou.avaliacao_tecnica_falou.model.Example

interface ExampleRepository {
    suspend fun addExample(definition: Example):Long
    suspend fun getExamples(id_definition: Long): List<Example>?
}