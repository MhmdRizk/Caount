package com.example.caount2.foodlogging.rawcalories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.caount2.R
import com.example.caount2.appdb.databseconfig.AppDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class LogRawCaloriesFragment : Fragment() {


    private lateinit var editTextCalories: EditText
    private lateinit var editTextProteins: EditText
    private lateinit var editTextCarbs: EditText
    private lateinit var editTextFats: EditText
    private lateinit var btnLogRawCalories: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun getCurrentDateFormatted(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log_raw_calories, container, false)

        // Initialize views
        editTextCalories = view.findViewById(R.id.editTextCalories)
        editTextProteins = view.findViewById(R.id.editTextProteins)
        editTextCarbs = view.findViewById(R.id.editTextCarbs)
        editTextFats = view.findViewById(R.id.editTextFats)
        btnLogRawCalories = view.findViewById(R.id.btnLogRawCalories)

        // Set up button click listener
        btnLogRawCalories.setOnClickListener {
            if (validateFields()) {
                AppDatabase.getDatabase(requireContext()).consumedFoodEntryDao().also {
                    lifecycleScope.launch {
                        it.insertConsumedEntry(
                            editTextCalories.text.toString().toDouble(),
                            editTextProteins.text.toString().toDouble(),
                            editTextFats.text.toString().toDouble(),
                            editTextCarbs.text.toString().toDouble(),
                            Date(),getCurrentDateFormatted()
                        )
                    }
                }
            }
        }



        return view
    }

    private fun validateFields(): Boolean {
        val textCalories = editTextCalories.text.toString()
        val textProteins = editTextProteins.text.toString()
        val textCarbs = editTextCarbs.text.toString()
        val textFats = editTextFats.text.toString()

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