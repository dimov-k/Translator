package ru.mrroot.translator

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.mrroot.translator.di.application
import ru.mrroot.translator.di.historyScreen
import ru.mrroot.translator.di.mainScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }

}