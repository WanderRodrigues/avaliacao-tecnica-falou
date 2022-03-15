package com.falou.avaliacao_tecnica_falou.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.falou.avaliacao_tecnica_falou.db.entity.WordEntity

@Dao
interface WordDao {
    @Insert
    suspend fun addWord(wordEntity: WordEntity): Long

    @Query("SELECT * FROM word WHERE word = :word")
    suspend fun getWord(word: String): WordEntity?
}