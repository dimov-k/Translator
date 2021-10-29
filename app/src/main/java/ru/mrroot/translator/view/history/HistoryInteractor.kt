package ru.mrroot.translator.view.history

import ru.mrroot.translator.model.entity.AppState
import ru.mrroot.translator.model.repository.IRepository
import ru.mrroot.translator.model.repository.Repository


class HistoryInteractor(private val repository: IRepository) {

    suspend fun getHistory(): AppState {
        return AppState.Success(repository.getAllHistory())
    }
}
