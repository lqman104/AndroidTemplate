package com.luqman.template.data.repository

import com.luqman.template.core.model.Resource
import com.luqman.template.core.model.ResourceText
import com.luqman.template.data.repository.model.Response

class DataRepository(
    private val localDataSource: DataSource,
    private val remoteDataSource: DataSource
): DataSource {

    override suspend fun fetch(): Resource<List<Response>> {
        return try {
//            remoteDataSource.fetch()
            localDataSource.fetch()
        } catch (e: Exception) {
            Resource.Error(error = ResourceText.Plain(e.message.orEmpty()), e)
        }
    }

}