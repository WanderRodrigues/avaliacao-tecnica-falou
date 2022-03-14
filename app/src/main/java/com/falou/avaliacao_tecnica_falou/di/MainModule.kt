package com.falou.avaliacao_tecnica_falou.di

import com.falou.avaliacao_tecnica_falou.data.remote.OxfordService
import com.falou.avaliacao_tecnica_falou.data.repository.DictionaryRepository
import com.falou.avaliacao_tecnica_falou.data.repository.DictionaryRepositoryImpl
import com.falou.avaliacao_tecnica_falou.domain.GetDictionaryUseCase
import com.falou.avaliacao_tecnica_falou.domain.GetDictionaryUseCaseImpl
import com.falou.avaliacao_tecnica_falou.service.ServiceGenerator
import com.falou.avaliacao_tecnica_falou.ui.dictionary.DictionaryViewModel
import com.falou.avaliacao_tecnica_falou.ui.search_word.SearchWordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {

    fun allModules() = listOf(
        viewModels, useCases, repositories, services
    )

    private val viewModels = module {
        viewModel { DictionaryViewModel(get()) }
        viewModel { SearchWordViewModel() }
    }

    private val useCases = module {
        single<GetDictionaryUseCase> { GetDictionaryUseCaseImpl(get()) }
    }

    private val repositories = module{
        single<DictionaryRepository>{ DictionaryRepositoryImpl(get()) }
    }

    private val services = module {
        single { ServiceGenerator().generate() as OxfordService }
    }
}