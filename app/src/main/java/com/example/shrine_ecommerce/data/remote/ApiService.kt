package com.example.shrine_ecommerce.data.remote

import com.example.shrine_ecommerce.data.remote.response.CategoryResponse
import com.example.shrine_ecommerce.data.remote.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 0,
    ): ProductResponse

    @GET("categories")
    suspend fun getCategories(
        @Query("page") page: Int = 0,
    ): CategoryResponse
}
