package com.app.mvvmstructure.rest

import com.app.mvvmstructure.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getProduct(): Flow<ProductResponse> = flow {
        emit(apiService.getProduct())
    }.flowOn(Dispatchers.IO)
}