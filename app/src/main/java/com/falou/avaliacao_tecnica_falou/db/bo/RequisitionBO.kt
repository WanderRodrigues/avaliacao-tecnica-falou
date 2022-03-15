package com.falou.avaliacao_tecnica_falou.db.bo

import android.content.Context
import android.util.Log
import com.falou.avaliacao_tecnica_falou.db.entity.RequisitionEntity
import com.falou.avaliacao_tecnica_falou.db.repository.requisition.RoomRequisitionDataSource
import com.falou.avaliacao_tecnica_falou.db.utils.DateUtils
import com.falou.avaliacao_tecnica_falou.model.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class RequisitionBO(context: Context) {
    val  requisitionDataSource =  RoomRequisitionDataSource(context)
    val dateUtil = DateUtils()

    suspend fun addRequisition() {
        try {

            requisitionDataSource.addRequisition(RequisitionEntity(Date().time,  dateUtil.convertDateToString(Date()))

            )
        }catch (ex: Exception){
            Log.e("ex SaveRequisition", ex.message?:"")
        }

    }
    //Validation to know if it has already been the 10 daily requests
    suspend fun IsAllowed() : Boolean{
        var requisitionsCurrent : ArrayList<Requisition>? = ArrayList()
        requisitionDataSource.get()?.forEach {
            val dateRequisition = dateUtil.convertStringToDate(it.date)
            if ( dateUtil.getDay(dateRequisition) == dateUtil.getDay(Date())){
                requisitionsCurrent?.add(it)
            }
        }

        return requisitionsCurrent?.size?:0 < 10
    }

}