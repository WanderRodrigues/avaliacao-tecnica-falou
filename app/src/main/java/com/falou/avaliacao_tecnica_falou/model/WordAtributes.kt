package com.falou.avaliacao_tecnica_falou.model

import com.falou.avaliacao_tecnica_falou.data.model.Pronunciation
import com.falou.avaliacao_tecnica_falou.data.model.Senses

data class WordAtributes(
    var definition: String ? = "",
    var examples: List<String>? = listOf(),
)