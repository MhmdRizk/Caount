package com.example.caount2.foodlogging.fooditem

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caount2.R
import com.example.caount2.appdb.databseconfig.AppDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class LogItemFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodItemAdapter: FoodItemAdapter
    private var foodItemList: List<FoodItemCell> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log_item, container, false)


        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        AppDatabase.getDatabase(requireContext()).also {
            lifecycleScope.launch {
                it.foodItemDao().getAllFoodItems().also {
                    foodItemList = it.map { foodItem ->
                        FoodItemCell(
                            foodItem.name,
                            foodItem.calories,
                            foodItem.proteins,
                            foodItem.fats,
                            foodItem.carbs
                        )
                    }
                    foodItemAdapter = FoodItemAdapter(foodItemList) { foodItemCell ->
                        onFoodItemClicked(foodItemCell)
                    }
                    recyclerView.adapter = foodItemAdapter
                    foodItemAdapter.notifyDataSetChanged()
                }
            }
        }


        return view
    }

    fun getCurrentDateFormatted(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun onFoodItemClicked(foodItem: FoodItemCell) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Enter amount")
        builder.setMessage("Please enter amount consumed in grams:")
        val input = EditText(context)
        builder.setView(input)
        builder.setPositiveButton("OK") { dialog, which ->
            val userInput = input.text.toString()
            val parsedInput = userInput.toDoubleOrNull()
            if (parsedInput != null) {
                val caloriesConsumed = parsedInput * foodItem.calories / 100
                val proteinsConsumed = parsedInput * foodItem.protein / 100
                val carbsConsumed = parsedInput * foodItem.carbs / 100
                val fatsConsumed = parsedInput * foodItem.fat / 100

                AppDatabase.getDatabase(requireContext()).consumedFoodEntryDao().also {
                    lifecycleScope.launch {
                        it.insertConsumedEntry(
                            caloriesConsumed, proteinsConsumed, fatsConsumed, carbsConsumed,
                            Date(),
                            getCurrentDateFormatted()
                        )

                        Toast.makeText(requireContext(), "Item logged successfully", Toast.LENGTH_SHORT)
                            .show()
                        activity?.finish()
                    }
                }

            } else {
                // Input is not a valid double
                Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                // Optionally, you can keep the dialog open or dismiss it based on your requirements
            }

        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            // Cancel action, if needed
        }

        val dialog = builder.create()
        dialog.show()

    }


}