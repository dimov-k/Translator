package ru.mrroot.translator.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mrroot.translator.di.ViewModelFactory
import ru.mrroot.translator.di.ViewModelKey
import ru.mrroot.translator.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}
