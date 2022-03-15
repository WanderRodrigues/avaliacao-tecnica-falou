package com.falou.avaliacao_tecnica_falou.mock

import com.falou.avaliacao_tecnica_falou.model.Language

class ListLanguages {

    fun listLanguage(): List<Language>{
        return listOf(
            Language("en-gb","ENGLISH", "english"),
            Language("es","SPANISH", "spanish"),
            Language("fr","FRENCH", "french")
        )
    }
}