package com.example.hilt2024.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hilt2024.App
import com.example.hilt2024.api.ApiService
import com.example.hilt2024.database.ProductDatabase
import com.example.hilt2024.model.ProductItem
import com.example.hilt2024.utils.NetworkUtils
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDatabase: ProductDatabase

) {

    private var productMutableData = MutableLiveData<MutableList<ProductItem>>()

    val products: LiveData<MutableList<ProductItem>>
        get() = productMutableData

    suspend fun getProductsData() {
        if (NetworkUtils.isInternetAvailable(App.getApplicationContext())) {
            val result = apiService.getProductData()
            if (result.body() != null) {

                productDatabase.productDao().addProductsData(result.body()!!)
                productMutableData.postValue(result.body()!!)
            }
        } else {
            val products: MutableList<ProductItem> = productDatabase.productDao()
                .readAllProductsData().toMutableList()

            productMutableData.postValue(products)
        }
    }
}