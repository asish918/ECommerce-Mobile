package com.example.shrine_ecommerce.data.remote.response

import com.example.shrine_ecommerce.model.Category
import com.example.shrine_ecommerce.model.Products
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("storeId")
    val storeId: String,
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("isFeatured")
    val isFeatured: Boolean,
    @SerializedName("isArchived")
    val isArchived: Boolean,
    @SerializedName("sizeId")
    val sizeId: String,
    @SerializedName("colorId")
    val colorId: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("images")
    val images: List<Products.Images>,
    @SerializedName("category")
    val category: List<Category>,
    @SerializedName("color")
    val color: List<Products.Color>,
    @SerializedName("size")
    val size: List<Products.Size>,
)
