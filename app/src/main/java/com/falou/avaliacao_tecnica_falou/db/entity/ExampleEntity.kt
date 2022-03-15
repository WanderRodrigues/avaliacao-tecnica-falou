package com.falou.avaliacao_tecnica_falou.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example")
class ExampleEntity(
    @ColumnInfo(name = "id_example")
    @PrimaryKey
    val idExample: Long,
    @ColumnInfo(name = "id_definition")
    val idDefinition: Long,
    @ColumnInfo(name = "example_detail")
    val exampleDetail: String
)