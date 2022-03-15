package com.falou.avaliacao_tecnica_falou.db.repository.word

import android.content.Context
import com.falou.avaliacao_tecnica_falou.db.DatabaseService
import com.falou.avaliacao_tecnica_falou.db.entity.WordEntity
import com.falou.avaliacao_tecnica_falou.model.Word

class RoomWordDataSource (context: Context): WordRepository {
    val wordDao = DatabaseService.getInstance(context).wordDao()

    override suspend fun add(word: Word): Long {
        val wordEntity = WordEntity(
            word.idWord,
            word.word,
            word.audioFile,
            word.phoneticSpelling
        )
        return wordDao.addWord(wordEntity)
    }

    override suspend fun get(word: String): Word? {
      val wordEntity =  wordDao.getWord(word)

        val word = Word(
            wordEntity?.idWord?:0,
            wordEntity?.word?:"",
            wordEntity?.audioFile?:"",
            wordEntity?.phoneticSpelling?:""
        )
        return word
    }

}