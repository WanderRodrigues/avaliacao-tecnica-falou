package com.falou.avaliacao_tecnica_falou.db.repository.definition

import android.content.Context
import com.falou.avaliacao_tecnica_falou.db.DatabaseService
import com.falou.avaliacao_tecnica_falou.db.entity.DefinitionEntity
import com.falou.avaliacao_tecnica_falou.model.Definition
import java.util.ArrayList

class RoomDefinitionDataSource (context: Context): DefinitionRepository {
    val definitionDao = DatabaseService.getInstance(context).definitionDao()

    override suspend fun addDefinition(definition: Definition): Long {
        val definitionEntity = DefinitionEntity(
            definition.idDefinition,
            definition.idWord,
            definition.detailDefinition
        )
        return definitionDao.addDefinition(definitionEntity)
    }

    override suspend fun get(id_word: Long): List<Definition>? {
        val listDefinitions: ArrayList<Definition> = ArrayList()
         definitionDao.getDefinitions(id_word)?.forEach {
             listDefinitions.add(Definition(it.idDefinition,it.idWord, it.definitionDetail))
         }
        return listDefinitions
    }

}