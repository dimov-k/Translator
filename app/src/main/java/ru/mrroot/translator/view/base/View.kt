package ru.mrroot.translator.view.base

import ru.mrroot.translator.model.entity.AppState

interface View {
    fun renderData(appState: AppState)
}