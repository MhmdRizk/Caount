package com.example.caount2.appdb.databseconfig


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.caount2.appdb.daos.ConsumedFoodEntryDao
import com.example.caount2.appdb.daos.FoodItemDao
import com.example.caount2.appdb.entities.ConsumedFoodEntry
import com.example.caount2.appdb.entities.FoodItem
import com.example.caount2.appdb.helpers.DateConverter

@Database(entities = [FoodItem::class, ConsumedFoodEntry::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodItemDao(): FoodItemDao
    abstract fun consumedFoodEntryDao(): ConsumedFoodEntryDao




    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "food_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
