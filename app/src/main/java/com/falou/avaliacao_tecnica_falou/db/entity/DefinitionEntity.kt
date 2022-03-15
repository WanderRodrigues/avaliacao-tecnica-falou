package com.falou.avaliacao_tecnica_falou.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definition")
class DefinitionEntity(
    @ColumnInfo(name = "id_definition")
    @PrimaryKey
    val idDefinition: Long,
    @ColumnInfo(name = "id_word")
    val idWord: Long,
    @ColumnInfo(name = "definition_detail")
    val definitionDetail: String
)