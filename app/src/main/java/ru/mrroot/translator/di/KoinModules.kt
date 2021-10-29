package ru.mrroot.translator.di

import androidx.room.Room
import ru.mrroot.translator.api.ApiHolder
import ru.mrroot.translator.model.cache.Cache
import ru.mrroot.translator.model.cache.ICache
import ru.mrroot.translator.model.repository.IRepository
import ru.mrroot.translator.model.repository.Repository
import ru.mrroot.translator.networkStatus.AndroidNetworkStatus
import ru.mrroot.translator.networkStatus.INetworkStatus
import ru.mrroot.translator.room.HistoryDataBase
import ru.mrroot.translator.view.history.HistoryInteractor
import ru.mrroot.translator.view.history.HistoryViewModel
import ru.mrroot.translator.view.main.MainInteractor
import ru.mrroot.translator.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<INetworkStatus> { AndroidNetworkStatus(get()) }
    single { ApiHolder().api }
    single {
        Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<HistoryDataBase>().historyDao() }
    single<ICache> { Cache(get()) }
    single<IRepository> { Repository(get(), get(), get()) }
}

val mainScreen = module {
    factory { MainInteractor(get()) }
    viewModel { MainViewModel(get()) }
}

val historyScreen = module {
    viewModel { HistoryViewModel(get()) }
    factory { HistoryInteractor(get()) }
}