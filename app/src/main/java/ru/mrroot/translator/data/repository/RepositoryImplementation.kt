package ru.mrroot.translator.data.repository

import ru.mrroot.translator.data.datasource.DataSource
import ru.mrroot.translator.data.word.Word

class RepositoryImplementation(private val dataSource: DataSource<List<Word>>) :
    Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}
