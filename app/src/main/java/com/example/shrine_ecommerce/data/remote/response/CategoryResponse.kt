package com.example.shrine_ecommerce.data.remote.response

import com.example.shrine_ecommerce.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("storeId")
    val storeId: String,
    @SerializedName("billboardId")
    val billboardId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("createdAt")
    val createdAt: String,
)