package com.falou.avaliacao_tecnica_falou

import android.app.Application
import com.falou.avaliacao_tecnica_falou.di.MainModule
import com.falou.avaliacao_tecnica_falou.utils.KoinUtilities
import org.koin.core.context.loadKoinModules


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        KoinUtilities.loadKoin(applicationContext)
        loadKoinModules(MainModule.allModules())
    }


}