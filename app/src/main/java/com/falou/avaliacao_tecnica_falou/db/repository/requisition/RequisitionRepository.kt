package com.falou.avaliacao_tecnica_falou.db.repository.requisition

import com.falou.avaliacao_tecnica_falou.db.entity.RequisitionEntity
import com.falou.avaliacao_tecnica_falou.model.Requisition

import java.util.*

interface RequisitionRepository {
    suspend fun addRequisition(requisitionEntity: RequisitionEntity):Long
    suspend fun get(): List<Requisition>?
}