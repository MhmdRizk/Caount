package com.example.caount2.foodlogging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.caount2.R

class LogOptionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log_options, container, false)
        // Find buttons by ID
        val btnAddItem = view.findViewById<Button>(R.id.btnAddItem)
        val btnLogItem = view.findViewById<Button>(R.id.btnLogItem)
        val btnLogRawCalories = view.findViewById<Button>(R.id.btnLogRawCalories)
        val btnAddMeal = view.findViewById<Button>(R.id.btnAddMeal)
        val btnLogMeal = view.findViewById<Button>(R.id.btnLogMeal)


        // Set onClickListener for btnLogItem
        btnAddItem.setOnClickListener {
            println("tapped add item")
        }

        // Set onClickListener for btnLogItem
        btnLogItem.setOnClickListener {
            println("tapped log item")
        }

        // Set onClickListener for btnLogRawCalories
        btnLogRawCalories.setOnClickListener {
            println("tapped log raw")

        }

        // Set onClickListener for btnLogMeal
        btnAddMeal.setOnClickListener {
            println("tapped add meal")

        }

        // Set onClickListener for btnLogMeal
        btnLogMeal.setOnClickListener {
            println("tapped log meal")

        }

        return view
    }
}