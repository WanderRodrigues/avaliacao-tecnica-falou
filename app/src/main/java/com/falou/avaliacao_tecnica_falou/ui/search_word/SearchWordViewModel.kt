package com.falou.avaliacao_tecnica_falou.ui.search_word

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falou.avaliacao_tecnica_falou.mock.ListLanguages
import com.falou.avaliacao_tecnica_falou.model.Language

class SearchWordViewModel () : ViewModel() {

    private val _isVisibleButton = MutableLiveData<Boolean>()
    val isVisibleButton : MutableLiveData<Boolean> = _isVisibleButton

    private val _languages = MutableLiveData<List<Language>>()
    val languages : MutableLiveData<List<Language>> = _languages

    fun setIsVisibleButton(isVisible: Boolean){
        _isVisibleButton.value = isVisible
    }

    init {
        getLanguages()
    }

    private fun getLanguages(){
        _languages.value =  ListLanguages().listLanguage()
    }

}