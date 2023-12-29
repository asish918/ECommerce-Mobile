package com.example.shrine_ecommerce.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shrine_ecommerce.data.repository.CategoryRepository
import com.example.shrine_ecommerce.data.repository.ProductRepository
import com.example.shrine_ecommerce.model.Category
import com.example.shrine_ecommerce.model.Products
import com.example.shrine_ecommerce.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private var _categories = mutableStateListOf(
        Category(
        billboardId = "",
        storeId = "",
        name = "All",
        createdAt = "",
        id = "all",
        updatedAt = ""
    )
    )
    val categoryList: SnapshotStateList<Category> = _categories

    var selectedCategory: MutableState<Category> = mutableStateOf(
        Category(
            billboardId = "",
            storeId = "",
            name = "All",
            createdAt = "",
            id = "all",
            updatedAt = ""
        )
    )

    private var _products = mutableStateListOf(Products(
        storeId = "",
        name = "All",
        createdAt = "",
        id = "all",
        updatedAt = "",
        color = Products.Color(
            updatedAt = "",
            id = "",
            createdAt = "",
            storeId = "",
            value = "",
            name = ""
        ),
        category = Category(
            id = "",
            name = "",
            storeId = "",
            createdAt = "",
            updatedAt = "",
            billboardId = ""
        ),
        categoryId = "",
        colorId = "",
        images = emptyList(),
        isArchived = false,
        isFeatured = false,
        price = "",
        size = Products.Size(
            name = "",
            updatedAt = "",
            createdAt = "",
            storeId = "",
            id = "",
            value = ""
        ),
        sizeId = ""
    ))

    val productList: SnapshotStateList<Products> = _products

    init {
        refreshAll()
    }

    private fun refreshAll(
        categoryId: String? = selectedCategory.value.id,
    ) {
        if (categoryId == "all") {
            selectedCategory.value = Category(
                billboardId = "",
                storeId = "",
                name = "All",
                createdAt = "",
                id = "all",
                updatedAt = ""
            )
        }

        fetchCategories()
        fetchProducts(categoryId)
    }

    fun filterBySetSelectedCategory(category: Category) {
        selectedCategory.value = category
        refreshAll(category.id)
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            val defaultCategory = Category(
                billboardId = "",
                storeId = "",
                name = "All",
                createdAt = "",
                id = "all",
                updatedAt = ""
            )
            when (val results = categoryRepository.getCategoryList()) {
                is Resource.Success -> {
                    _categories.clear()
                    _categories.add(defaultCategory)
                    selectedCategory.value = defaultCategory
                    results.data?.forEach {
                        _categories.add(it)
                    }
                }
                is Resource.Error -> {
                    Timber.e("Error loading Categories: ${results.statusMessage}")
                }
                else -> { }
            }
        }
    }

    private fun fetchProducts(categoryId: String?) {
        viewModelScope.launch {
            when (val results = productRepository.getProductList()) {
                is Resource.Success -> {
                    _products.clear()
                    if (categoryId != "all") {
                        results.data?.filter {
                            it.categoryId == categoryId
                        }?.forEach {
                            _products.add(it)
                        }
                    } else {
                        results.data?.forEach {
                            _products.add(it)
                        }
                    }
                }

                is Resource.Error -> {
                    Timber.e("Error loading Products")
                }

                else -> {}
            }

        }
    }
}