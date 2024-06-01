package com.example.caount2.appdb.daos

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.caount2.appdb.entities.FoodItem


@Dao
interface FoodItemDao {

    @Insert
    suspend fun insert(entity: FoodItem)

    @Query("SELECT * FROM food_item")
    suspend fun getAllFoodItems(): List<FoodItem>

    @Transaction
    suspend fun insertFoodItem(name: String, calories: Double, protein: Double, fat: Double, carbs: Double) {
        val foodItem = FoodItem(name, fat, carbs, protein, calories)
        insert(foodItem)
    }

}
