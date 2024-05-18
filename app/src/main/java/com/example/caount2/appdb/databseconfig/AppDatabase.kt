package com.example.caount2.appdb.databseconfig


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.caount2.appdb.daos.FoodItemDao
import com.example.caount2.appdb.entities.FoodItem

@Database(entities = [FoodItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodItemDao(): FoodItemDao
}
