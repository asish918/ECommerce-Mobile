package com.example.shrine_ecommerce.data.repository

import com.example.shrine_ecommerce.data.remote.ApiService
import com.example.shrine_ecommerce.data.remote.response.ProductResponse
import com.example.shrine_ecommerce.utils.Resource
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getProductList(): Resource<ProductResponse> {
        val response = try {
            api.getProducts(1)
        } catch (e: Exception) {
            return Resource.Error("Error when loading products")
        }
        return Resource.Success(response)
    }
}
