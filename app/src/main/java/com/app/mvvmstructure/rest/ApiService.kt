package com.app.mvvmstructure.rest

import com.app.mvvmstructure.model.ProductResponse
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProduct(): ProductResponse
}