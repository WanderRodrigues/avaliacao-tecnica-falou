package com.falou.avaliacao_tecnica_falou.ui.dictionary

import android.util.Log
import androidx.lifecycle.*
import com.falou.avaliacao_tecnica_falou.RealtimeRepository
import com.falou.avaliacao_tecnica_falou.data.model.Dictionary
import com.falou.avaliacao_tecnica_falou.data.model.Pronunciation
import com.falou.avaliacao_tecnica_falou.data.model.Senses
import com.falou.avaliacao_tecnica_falou.domain.GetDictionaryUseCase
import com.falou.avaliacao_tecnica_falou.extensions.read
import com.falou.avaliacao_tecnica_falou.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictionaryViewModel (
    private val getDictionaryUseCase: GetDictionaryUseCase
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

    private var _senses = MutableLiveData<List<Senses>>()
    val senses: LiveData<List<Senses>> = _senses

    private var _pronunciations = MutableLiveData<List<Pronunciation>>()
    val pronunciations: LiveData<List<Pronunciation>> = _pronunciations


    fun getDictionary(language: String, word: String) = viewModelScope.launch {
        _status.value = Status.LOADING

        withContext(Dispatchers.IO) {
            getDictionaryUseCase(language,word)
        }.let {
            it.read({ dictionary->
                _status.value = Status.SUCCESS

                _word.value = dictionary.word?:""
                dictionary.results?.forEach { results ->
                    results.lexicalEntries.forEach { lexicalEntries ->
                        lexicalEntries.entries.forEach { entries ->
                            _senses.value = entries.senses
                            _pronunciations.value = entries.pronunciations
                        }

                    }
                }

                populateTextView()

                RealtimeRepository().saveNewWord(
                    word,
                    senses.value?: listOf(),
                    pronunciations.value?: listOf()
                )


            }, {
                _status.value = Status.ERROR
                Log.e("Exception", it.message?:"")
            })
        }
    }

    //Check if word has already been searched
    fun checkWord(language: String ,word: String)  = viewModelScope.launch{
        _status.value = Status.LOADING
        withContext(Dispatchers.Main) {

            val searchedWord =  RealtimeRepository().getWord(word)

            if (searchedWord != null){
                _word.value = searchedWord.idWord?:""
                _senses.value = searchedWord.senses?: listOf()
                _pronunciations.value = searchedWord.pronunciation?: listOf()

                _status.value = Status.SUCCESS
            }else{
                getDictionary(language, word)
            }
        }



    }

    fun populateTextView(){
        if (!word.value.isNullOrEmpty()){
            _title.value = word.value
        }
        if (pronunciations.value?.isNotEmpty() == true) {
            _phoneticSpelling.value = pronunciations.value?.first()?.phoneticSpelling ?: ""
            _hasSound.value = true
        }
    }

    fun setIsPlaying(isPlaying: Boolean){
        _isPlaying.value = isPlaying
    }
}