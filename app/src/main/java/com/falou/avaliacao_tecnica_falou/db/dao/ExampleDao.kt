package com.falou.avaliacao_tecnica_falou.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.falou.avaliacao_tecnica_falou.db.entity.ExampleEntity


@Dao
interface ExampleDao {
    @Insert
    suspend fun addExample(exampleEntity: ExampleEntity): Long

    @Query("SELECT * FROM example WHERE id_definition = :id_definitions")
    suspend fun getExamples(id_definitions: Long): List<ExampleEntity>?
}