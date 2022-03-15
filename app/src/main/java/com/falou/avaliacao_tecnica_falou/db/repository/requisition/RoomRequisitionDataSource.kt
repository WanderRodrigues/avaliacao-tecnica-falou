package com.falou.avaliacao_tecnica_falou.db.repository.requisition

import android.content.Context
import com.falou.avaliacao_tecnica_falou.db.DatabaseService
import com.falou.avaliacao_tecnica_falou.db.entity.RequisitionEntity
import com.falou.avaliacao_tecnica_falou.model.Requisition
import java.util.*

class RoomRequisitionDataSource (context: Context): RequisitionRepository {
    val requisitionDao = DatabaseService.getInstance(context).requisitionDao()

    override suspend fun addRequisition(requisitionEntity: RequisitionEntity): Long {
        val requisitionEntity = RequisitionEntity(
            requisitionEntity.id_requisition,
            requisitionEntity.date
        )
        return requisitionDao.addRequisition(requisitionEntity)
    }

    override suspend fun get(): List<Requisition>? {
        val listRequisition: ArrayList<Requisition>?= ArrayList()
        requisitionDao.getRequisition()?.forEach {
            listRequisition?.add(Requisition(it.id_requisition,it.date))
        }
        return listRequisition
    }

}