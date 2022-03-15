package com.falou.avaliacao_tecnica_falou.utils

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.startKoin

object KoinUtilities {

     fun loadKoin(context: Context) {

        KoinContextHandler.getOrNull() ?: run {
            startKoin {
                androidContext(context)
            }
        }
    }
}
