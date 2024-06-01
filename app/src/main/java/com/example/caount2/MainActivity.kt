package com.example.caount2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room.databaseBuilder
import com.example.caount2.appdb.databseconfig.AppDatabase
import com.example.caount2.foodlogging.parent.FoodLoggingActivity
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }


    private lateinit var yourCalorieIntake: TextView
    private lateinit var yourFatIntake: TextView
    private lateinit var yourProteinIntake: TextView
    private lateinit var yourCarbsIntake: TextView

    fun getCurrentDateFormatted(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()

        setContentView(R.layout.activity_main)

        val slider: MaterialDrawerSliderView = findViewById(R.id.slider);

        val home = PrimaryDrawerItem().apply { nameRes = R.string.drawer_item_home; identifier = 1 }
        val settings =
            SecondaryDrawerItem().apply { nameRes = R.string.drawer_item_settings; identifier = 2 }



        slider.itemAdapter.add(
            home,
            DividerDrawerItem(),
            settings
        )


        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            // do something with the clicked item :D
            println("Rizk:- tapped on drawer")
            false
        }


        val sideMenuIcon: ImageView = findViewById(R.id.side_menu_icon)
        sideMenuIcon.setOnClickListener {
            slider.drawerLayout?.openDrawer(slider)
        }


        val addLogButton: Button = findViewById(R.id.add_button)
        addLogButton.setOnClickListener {
            val intent = Intent(this@MainActivity, FoodLoggingActivity::class.java)
            startActivity(intent) // Start the NewActivity

        }


        yourCalorieIntake = findViewById(R.id.your_calorie_intake)
        yourFatIntake = findViewById(R.id.your_fat_intake)
        yourProteinIntake = findViewById(R.id.your_protein_intake)
        yourCarbsIntake = findViewById(R.id.your_carbs_intake)



    }

    fun reloadStats(){
        AppDatabase.getDatabase(this).consumedFoodEntryDao().also {
            lifecycleScope.launch {
                it.getFoodItemsByDateString(getCurrentDateFormatted()).also { list ->
                    var calories: Double = 0.0
                    var proteins: Double = 0.0
                    var carbs: Double = 0.0
                    var fats: Double = 0.0
                    list.forEach {
                            item ->
                        calories += item.calories
                        proteins += item.proteins
                        carbs += item.carbs
                        fats += item.fats

                    }

                    yourCalorieIntake.text = "you consumed \n" + calories.toInt().toString() + " Calories till now"
                    yourFatIntake.text = "Fats: " + fats.toInt().toString() + "g"
                    yourProteinIntake.text = "Proteins: " + proteins.toInt().toString() + "g"
                    yourCarbsIntake.text = "Carbs: " + carbs.toInt().toString() + "g"


                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        reloadStats()
    }

}