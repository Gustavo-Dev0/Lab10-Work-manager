package com.dev0.deliveryfood.feacture_news.data.repository


import com.dev0.deliveryfood.feacture_news.data.data_source.NewDao
import com.dev0.deliveryfood.feacture_news.domain.model.New
import com.dev0.deliveryfood.feacture_news.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow


class NewRepositoryImpl (
    private val dao: NewDao
): NewRepository {
    override fun getNews(): Flow<List<New>> {
        return dao.getAll()
    }

    override fun deleteNews() {
        dao.deleteAll()
    }

    override fun insert(n: New) {
        dao.insert(n)
    }


}