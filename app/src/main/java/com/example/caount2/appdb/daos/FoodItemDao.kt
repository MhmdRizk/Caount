package com.example.caount2.appdb.daos

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.example.caount2.appdb.entities.FoodItem


@Dao
interface FoodItemDao {

    @Insert
    suspend fun insert(entity: FoodItem)

    @Query("SELECT * FROM food_item")
    suspend fun getAllFoodItems(): List<FoodItem>

}