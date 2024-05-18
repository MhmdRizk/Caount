package com.example.caount2

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.caount2.ui.theme.Caount2Theme
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.model.interfaces.withIdentifier
import com.mikepenz.materialdrawer.model.interfaces.withName
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slider: MaterialDrawerSliderView = findViewById(R.id.slider);

        val home = PrimaryDrawerItem().apply { nameRes = R.string.drawer_item_home; identifier = 1 }
        val settings =
            SecondaryDrawerItem().apply { nameRes = R.string.drawer_item_settings; identifier = 2 }



        slider.itemAdapter.add(
            home,
            DividerDrawerItem(),
            settings)


        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            // do something with the clicked item :D
            println("Rizk:- tapped on drawer")
            false
        }


        val sideMenuIcon: ImageView = findViewById(R.id.side_menu_icon)
        sideMenuIcon.setOnClickListener {
            slider.drawerLayout?.openDrawer(slider)
        }


    }


}