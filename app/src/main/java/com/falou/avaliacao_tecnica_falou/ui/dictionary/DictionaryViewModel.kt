package com.falou.avaliacao_tecnica_falou.ui.dictionary

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.falou.avaliacao_tecnica_falou.db.bo.RequisitionBO
import com.falou.avaliacao_tecnica_falou.db.bo.WordBO
import com.falou.avaliacao_tecnica_falou.data.model.Dictionary
import com.falou.avaliacao_tecnica_falou.data.model.Pronunciation
import com.falou.avaliacao_tecnica_falou.db.repository.word.RoomWordDataSource
import com.falou.avaliacao_tecnica_falou.domain.GetDictionaryUseCase
import com.falou.avaliacao_tecnica_falou.extensions.read
import com.falou.avaliacao_tecnica_falou.model.WordAtributes
import com.falou.avaliacao_tecnica_falou.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DictionaryViewModel (
    private val getDictionaryUseCase: GetDictionaryUseCase,
    ) : ViewModel() {

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    private var _dictionary = MutableLiveData<Dictionary>()
    val dictionary: LiveData<Dictionary> = _dictionary

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private var _phoneticSpelling = MutableLiveData<String>()
    val phoneticSpelling: LiveData<String> get() = _phoneticSpelling

    private var _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    private var _hasSound = MutableLiveData(false)
    val hasSound: LiveData<Boolean> get() = _hasSound

    private var _word = MutableLiveData<String>()
    val word: LiveData<String> = _word

    private var _pronunciation = MutableLiveData<Pronunciation?>()
    val pronunciation: LiveData<Pronunciation?> = _pronunciation

    private var _wordAtributes = MutableLiveData<List<WordAtributes>>()
    val wordAtributes: LiveData<List<WordAtributes>> = _wordAtributes

    private var _requestExceeded = MutableLiveData<Boolean>()
    val requestExceeded: LiveData<Boolean> = _requestExceeded


    fun getDictionary(language: String, word: String, context: Context) = viewModelScope.launch {
        _status.value = Status.LOADING

        if (RequisitionBO(context).IsAllowed()){

            withContext(Dispatchers.IO) {
                getDictionaryUseCase(language,word)
            }.let {
                it.read({ dictionary->
                    _status.value = Status.SUCCESS
                    var listWordAtributes : ArrayList<WordAtributes> = ArrayList()
                    _word.value = dictionary.word?:""
                    dictionary.results?.forEach { results ->
                        results.lexicalEntries.forEach { lexicalEntries ->
                            lexicalEntries.entries.forEach { entries ->
                                entries.senses.forEach { senses->
                                    senses.definitions?.forEach { definition->
                                        var listExamples : ArrayList<String> = ArrayList()
                                        senses.examples?.forEach {example->
                                            listExamples.add(example.text!!)
                                        }
                                        listWordAtributes?.add(WordAtributes(definition, listExamples))
                                    }
                                }
                                _wordAtributes.value = listWordAtributes
                                if (entries.pronunciations!=null){
                                    _pronunciation.value = entries.pronunciations.firstOrNull()
                                }
                            }
                        }
                    }
                    populateTextView()
                    saveNewWord(context)

                }, {
                    _status.value = Status.ERROR
                    Log.e("Exception", it.message?:"")
                })
            }
        }else{
            _requestExceeded.postValue(true)
        }
    }

    fun saveNewWord(context: Context) = viewModelScope.launch{
        RequisitionBO(context).addRequisition()
        WordBO(context).saveWordSearched(
            word.value?:"",
            wordAtributes.value,
            pronunciation.value
        )
    }

    //Check if word has already been searched
    fun checkWord(language: String ,word: String, context: Context)  = viewModelScope.launch{
        _status.value = Status.LOADING
        withContext(Dispatchers.Main) {
            try {
                val searchedWord =  RoomWordDataSource(context).get(word.lowercase())

                if (!searchedWord?.word.isNullOrEmpty()){
                    _word.value = searchedWord?.word
                    _pronunciation.value = Pronunciation(searchedWord?.audioFile, searchedWord?.phoneticSpelling)
                    _wordAtributes.value = WordBO(context).getWordSearched(searchedWord?.idWord?:0)

                    populateTextView()
                    _status.value = Status.SUCCESS
                }else{
                    getDictionary(language, word, context)
                }
            }catch (ex : Exception){
                Log.e("Exception", ex.message?:"")
            }
        }
    }

    fun populateTextView(){
        if (!word.value.isNullOrEmpty()){
            _title.value = word.value
        }
        if (!pronunciation.value?.audioFile.isNullOrEmpty() && !pronunciation.value?.phoneticSpelling.isNullOrEmpty()) {
            _phoneticSpelling.value = pronunciation.value?.phoneticSpelling ?: ""
            _hasSound.value = true
        }
    }

    fun setIsPlaying(isPlaying: Boolean){
        _isPlaying.value = isPlaying
    }
}