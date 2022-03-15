package com.falou.avaliacao_tecnica_falou.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.falou.avaliacao_tecnica_falou.db.dao.DefinitionDao
import com.falou.avaliacao_tecnica_falou.db.dao.ExampleDao
import com.falou.avaliacao_tecnica_falou.db.dao.RequisitionDao
import com.falou.avaliacao_tecnica_falou.db.dao.WordDao
import com.falou.avaliacao_tecnica_falou.db.entity.DefinitionEntity
import com.falou.avaliacao_tecnica_falou.db.entity.ExampleEntity
import com.falou.avaliacao_tecnica_falou.db.entity.RequisitionEntity
import com.falou.avaliacao_tecnica_falou.db.entity.WordEntity

@Database(entities = [WordEntity::class, DefinitionEntity::class, ExampleEntity::class, RequisitionEntity::class], version = 1)
abstract class DatabaseService: RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "word_searched.db"
        private var instance: DatabaseService? = null
        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun wordDao(): WordDao
    abstract fun definitionDao(): DefinitionDao
    abstract fun exampleDao(): ExampleDao
    abstract fun requisitionDao(): RequisitionDao
}