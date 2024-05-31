package com.example.caount2.appdb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.caount2.appdb.entities.ConsumedFoodEntry
import java.util.Date


@Dao
interface ConsumedFoodEntryDao {

    @Insert
    suspend fun insert(entity: ConsumedFoodEntry)

    @Query("SELECT * FROM consumed_food_entry")
    suspend fun getAllFoodItems(): List<ConsumedFoodEntry>

    @Query("SELECT * FROM consumed_food_entry WHERE dateString = :dateString")
    suspend fun getFoodItemsByDateString(dateString: String): List<ConsumedFoodEntry>

    @Transaction
    suspend fun insertConsumedEntry(
        calories: Double,
        protein: Double,
        fat: Double,
        carbs: Double,
        date: Date,
        dateString: String
    ) {
        val consumedFoodEntry = ConsumedFoodEntry(fat, carbs, protein, calories, date, dateString)
        insert(consumedFoodEntry)
    }


}