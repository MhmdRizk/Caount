package com.example.caount2.foodlogging


import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.caount2.R

class FoodLoggingActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_logging)

        // Load the fragment into the fragment container
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LogOptionsFragment())
            .commit()

        val closeButton: ImageView = findViewById(R.id.closeIcon)
        closeButton.setOnClickListener {
            finish()
        }

    }
}