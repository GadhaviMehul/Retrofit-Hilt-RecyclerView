package com.example.hilt2024.api

import com.example.hilt2024.model.ProductItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/products")

    suspend fun getProductData():Response<MutableList<ProductItem>>
}