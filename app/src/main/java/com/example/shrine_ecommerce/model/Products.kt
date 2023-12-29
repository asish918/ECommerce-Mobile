package com.example.shrine_ecommerce.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products(
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
    val images: List<Images>,
    @SerializedName("category")
    val category: List<Category>,
    @SerializedName("color")
    val color: List<Color>,
    @SerializedName("size")
    val size: List<Size>,

) : Parcelable {
    @Parcelize
    data class Images(
        @SerializedName("id")
        val id: String,
        @SerializedName("productId")
        val productId: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
    ): Parcelable
    @Parcelize
    data class Color(
        @SerializedName("id")
        val id: String,
        @SerializedName("storeId")
        val storeId: String,
        @SerializedName("value")
        val value: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("createdAt")
        val createdAt: String,
    ): Parcelable
    @Parcelize
    data class Size(
        @SerializedName("id")
        val id: String,
        @SerializedName("storeId")
        val storeId: String,
        @SerializedName("value")
        val value: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("createdAt")
        val createdAt: String,
    ): Parcelable
}
