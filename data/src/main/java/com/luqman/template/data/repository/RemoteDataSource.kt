package com.luqman.template.data.repository

import com.luqman.template.core.model.Resource
import com.luqman.template.core.network.extension.ResponseExtension.runCatchingResponse
import com.luqman.template.data.repository.model.Response
import com.luqman.template.data.services.SomeService
import kotlinx.coroutines.CoroutineDispatcher

class RemoteDataSource(
    private val someService: SomeService,
    private val dispatcher: CoroutineDispatcher
) : DataSource {

    override suspend fun fetch(): Resource<List<Response>> {
        // TODO:: change with the real data
        return runCatchingResponse(dispatcher) {
            val data = someService.fetch().map {
                Response(1, it)
            }

            Resource.Success(data)
        }
    }
}