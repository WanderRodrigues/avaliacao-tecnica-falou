package com.falou.avaliacao_tecnica_falou.db.repository.example

import android.content.Context
import com.falou.avaliacao_tecnica_falou.db.DatabaseService
import com.falou.avaliacao_tecnica_falou.db.entity.ExampleEntity
import com.falou.avaliacao_tecnica_falou.model.Example
import java.util.ArrayList

class RoomExampleDataSource (context: Context): ExampleRepository {
    val exampleDao = DatabaseService.getInstance(context).exampleDao()

    override suspend fun addExample(example: Example): Long {
        val exampleEntity = ExampleEntity(
            example.idExample,
            example.idDefintion,
            example.detailExample
        )
        return exampleDao.addExample(exampleEntity)
    }

    override suspend fun getExamples(id_definition: Long): List<Example>? {
        val listExample: ArrayList<Example> = ArrayList()
        exampleDao.getExamples(id_definition)?.forEach {
            listExample.add(Example(it.idExample,it.idDefinition, it.exampleDetail))
        }
        return listExample
    }

}