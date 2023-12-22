package com.luqman.template.data.repository

import com.luqman.template.core.model.Resource
import com.luqman.template.data.repository.model.Response

interface DataSource {
    suspend fun fetch(): Resource<List<Response>>
}