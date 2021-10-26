package ru.mrroot.translator.app

import android.app.Application
import ru.mrroot.translator.di.AppComponent
import ru.mrroot.translator.di.DaggerAppComponent
import ru.mrroot.translator.di.module.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}
