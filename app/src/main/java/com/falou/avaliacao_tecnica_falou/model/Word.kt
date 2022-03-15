package com.falou.avaliacao_tecnica_falou.model

data class Word(
    var idWord: Long = 0L,
    var word: String= "",
    val audioFile: String ="",
    val phoneticSpelling: String
)