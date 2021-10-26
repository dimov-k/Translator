package ru.mrroot.translator.di.module

import ru.mrroot.translator.app.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}