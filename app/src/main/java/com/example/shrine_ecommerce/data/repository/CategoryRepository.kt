package com.example.shrine_ecommerce.data.repository

import android.util.Log
import com.example.shrine_ecommerce.data.remote.ApiService
import com.example.shrine_ecommerce.data.remote.response.CategoryResponse
import com.example.shrine_ecommerce.model.Category
import com.example.shrine_ecommerce.utils.Resource
import timber.log.Timber
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: ApiService,
) {
    suspend fun getCategoryList(): Resource<List<Category>> {
        val response = try {
            api.getCategories(1)
        } catch (e: Exception) {
            Timber.tag("Bimch").i(e.toString())
            return Resource.Error("Error when loading categories")
        }
        return Resource.Success(response)
    }
}
