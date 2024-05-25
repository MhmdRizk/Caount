package com.example.caount2.appdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_item")
data class FoodItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val fats: Double,
    val carbs: Double,
    val proteins: Double,
    val calories: Double
) {
    // Secondary constructor without id, Room will auto-generate the id
    constructor(name: String, fats: Double, carbs: Double, proteins: Double, calories: Double) :
            this(0L, name, fats, carbs, proteins, calories)
}
