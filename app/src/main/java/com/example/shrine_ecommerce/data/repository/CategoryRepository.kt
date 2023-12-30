package com.example.shrine_ecommerce.data.repository

import com.example.shrine_ecommerce.data.remote.ApiService
import com.example.shrine_ecommerce.model.Category
import com.example.shrine_ecommerce.utils.Resource
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: ApiService,
) {
    suspend fun getCategoryList(): Resource<List<Category>> {
        val response = try {
            api.getCategories(1)
        } catch (e: Exception) {
            return Resource.Error("Error when loading categories")
        }
        return Resource.Success(response)
    }
}
