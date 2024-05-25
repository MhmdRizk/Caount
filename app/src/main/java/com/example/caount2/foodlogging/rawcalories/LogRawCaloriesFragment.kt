package com.example.caount2.foodlogging.rawcalories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.caount2.R

class LogRawCaloriesFragment : Fragment() {


    private lateinit var textViewCalories: TextView
    private lateinit var textViewProteins: TextView
    private lateinit var textViewCarbs: TextView
    private lateinit var textViewFats: TextView
    private lateinit var btnLogRawCalories: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log_raw_calories, container, false)

        // Initialize views
        textViewCalories = view.findViewById(R.id.textViewCalories)
        textViewProteins = view.findViewById(R.id.textViewProteins)
        textViewCarbs = view.findViewById(R.id.textViewCarbs)
        textViewFats = view.findViewById(R.id.textViewFats)
        btnLogRawCalories = view.findViewById(R.id.btnLogRawCalories)

        // Set up button click listener
        btnLogRawCalories.setOnClickListener {
            if (validateFields()) {
                println("RIZK:- add log here")
            }
        }



        return view
    }

    private fun validateFields(): Boolean {
        val textCalories = textViewCalories.text.toString()
        val textProteins = textViewProteins.text.toString()
        val textCarbs = textViewCarbs.text.toString()
        val textFats = textViewFats.text.toString()

        if (textCalories.isEmpty() || textProteins.isEmpty() || textCarbs.isEmpty() || textFats.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        try {
            val valueCalories = textCalories.toDouble()
            val valueProteins = textProteins.toDouble()
            val valueCarbs = textCarbs.toDouble()
            val valueFats = textFats.toDouble()

            // All fields are filled and contain valid double values, proceed with your logic
            println("All fields are valid: $valueCalories, $valueProteins, $valueCarbs, $valueFats")
        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "Please enter valid numbers", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        return true

    }


}