package com.example.caount2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import com.example.caount2.appdb.databseconfig.AppDatabase
import com.example.caount2.foodlogging.parent.FoodLoggingActivity
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var database: AppDatabase
            private set
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


    }


}