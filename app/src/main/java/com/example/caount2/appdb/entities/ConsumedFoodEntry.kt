package com.example.caount2.appdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "consumed_food_entry")
data class ConsumedFoodEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val fats: Double,
    val carbs: Double,
    val proteins: Double,
    val calories: Double,
    val date: Date,
    val dateString: String
) {
    // Secondary constructor without id, Room will auto-generate the id
    constructor(fats: Double, carbs: Double, proteins: Double, calories: Double, date: Date,dateString: String) :
            this(0L, fats, carbs, proteins, calories, date, dateString)


}
