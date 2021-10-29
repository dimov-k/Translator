package ru.mrroot.translator.view.main

import ru.mrroot.translator.model.entity.AppState
import ru.mrroot.translator.model.repository.IRepository
import ru.mrroot.translator.viewModel.Interactor

class MainInteractor(private val repository: IRepository) : Interactor<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(repository.getWords(word))
    }
}