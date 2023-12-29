package com.example.shrine_ecommerce.data.repository

import com.example.shrine_ecommerce.data.remote.ApiService
import com.example.shrine_ecommerce.data.remote.response.ProductResponse
import com.example.shrine_ecommerce.model.Products
import com.example.shrine_ecommerce.utils.Resource
import timber.log.Timber
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService,
) {
    suspend fun getProductList(): Resource<List<Products>> {
        val response = try {
            api.getProducts(1)
        } catch (e: Exception) {
            Timber.tag("Bimch").i(e.toString())
            return Resource.Error("Error when loading products")
        }
        return Resource.Success(response)
    }
}
