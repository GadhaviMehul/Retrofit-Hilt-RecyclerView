package com.example.hilt2024.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hilt2024.model.ProductItem

@Database(entities = [ProductItem::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

}