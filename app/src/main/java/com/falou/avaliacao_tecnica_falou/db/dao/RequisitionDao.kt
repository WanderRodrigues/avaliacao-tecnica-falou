package com.falou.avaliacao_tecnica_falou.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.falou.avaliacao_tecnica_falou.db.entity.RequisitionEntity
import com.falou.avaliacao_tecnica_falou.db.entity.WordEntity
import java.util.*

@Dao
interface RequisitionDao {
    @Insert
    suspend fun addRequisition(requisitionEntity: RequisitionEntity): Long

    @Query("SELECT * FROM requisition")
    suspend fun getRequisition(): List<RequisitionEntity>?
}