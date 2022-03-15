package com.falou.avaliacao_tecnica_falou.db.bo

import android.content.Context
import android.util.Log
import com.falou.avaliacao_tecnica_falou.data.model.Pronunciation
import com.falou.avaliacao_tecnica_falou.db.repository.definition.RoomDefinitionDataSource
import com.falou.avaliacao_tecnica_falou.db.repository.example.RoomExampleDataSource
import com.falou.avaliacao_tecnica_falou.db.repository.word.RoomWordDataSource
import com.falou.avaliacao_tecnica_falou.model.Definition
import com.falou.avaliacao_tecnica_falou.model.Example
import com.falou.avaliacao_tecnica_falou.model.WordAtributes
import com.falou.avaliacao_tecnica_falou.model.Word
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class WordBO(context: Context) {
    val  wordDataSource =  RoomWordDataSource(context)
    val  defintionDataSource =  RoomDefinitionDataSource(context)
    val  exampleDataSource =  RoomExampleDataSource(context)

    suspend fun saveWordSearched(
        word:String,
        listAtributesWord: List<WordAtributes>?,
        pronunciation: Pronunciation?
        ) {
        try {
            val id_word = wordDataSource.add(
                Word(
                    Date().time,
                    word,
                    pronunciation?.audioFile?:"",
                    pronunciation?.phoneticSpelling?:"")
            )

            if (listAtributesWord?.isNotEmpty() == true){
                listAtributesWord.forEach { wordAtributes ->
                    val id_definition = defintionDataSource.addDefinition(Definition(Date().time, id_word, wordAtributes.definition?:""))

                    wordAtributes.examples?.forEach {example ->
                        exampleDataSource.addExample(Example(Date().time, id_definition, example))
                    }
                }
            }

        }catch (ex: Exception){
            Log.e("exceptionSave", ex.message?:"")
        }

    }

    suspend fun getWordSearched(id_word:Long) : List<WordAtributes>?{
        var listWordAtributes : ArrayList<WordAtributes>? = ArrayList()
        val definition = defintionDataSource.get(id_word)
        definition?.forEach { it ->
            //Transforming a list of examples, which is a list of an Example object, into a list of strings to be able to use a WordAttributes object
            var listExamples : ArrayList<String> = ArrayList()
            exampleDataSource.getExamples(it.idDefinition)?.forEach { example->
                listExamples.add(example.detailExample)
            }
            listWordAtributes?.add(WordAtributes(it.detailDefinition, listExamples ))
        }

        return listWordAtributes
    }

}