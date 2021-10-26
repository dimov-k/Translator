package ru.mrroot.translator.view.base

import ru.mrroot.translator.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}