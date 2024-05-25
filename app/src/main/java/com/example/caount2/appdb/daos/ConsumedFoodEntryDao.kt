package com.example.caount2.appdb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.caount2.appdb.entities.ConsumedFoodEntry
import java.util.Date


@Dao
interface ConsumedFoodEntryDao {

    @Insert
    suspend fun insert(entity: ConsumedFoodEntry)

//    @Query("SELECT * FROM ")
//    suspend fun getAllFoodItems(): List<FoodItem>

    @Transaction
    suspend fun insertConsumedEntry(
        calories: Double,
        protein: Double,
        fat: Double,
        carbs: Double,
        date: Date
    ) {
        val consumedFoodEntry = ConsumedFoodEntry(fat, carbs, protein, calories, date)
        insert(consumedFoodEntry)
    }


}