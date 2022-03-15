package com.falou.avaliacao_tecnica_falou.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.falou.avaliacao_tecnica_falou.db.entity.DefinitionEntity

@Dao
interface DefinitionDao {
    @Insert
    suspend fun addDefinition(definitionEntity: DefinitionEntity): Long

    @Query("SELECT * FROM definition WHERE id_word = :id_word")
    suspend fun getDefinitions(id_word: Long): List<DefinitionEntity>?
}