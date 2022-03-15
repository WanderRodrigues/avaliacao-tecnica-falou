package com.falou.avaliacao_tecnica_falou.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "requisition")
class RequisitionEntity(
    @ColumnInfo(name = "id_requisition")
    @PrimaryKey
    val id_requisition: Long,
    @ColumnInfo(name = "date")
    val date: String
)