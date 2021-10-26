package ru.mrroot.translator.di

import ru.mrroot.translator.di.module.AppModule
import ru.mrroot.translator.di.module.RepositoryModule
import ru.mrroot.translator.di.module.ViewModelModule
import ru.mrroot.translator.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}