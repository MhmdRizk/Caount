package com.example.caount2.foodlogging.fooditem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.caount2.R
import com.example.caount2.appdb.daos.FoodItemDao
import com.example.caount2.appdb.databseconfig.AppDatabase
import kotlinx.coroutines.launch


class AddItemFragment() : Fragment() {


    private lateinit var editTextItemName: EditText
    private lateinit var editTextItemCalories: EditText
    private lateinit var editTextItemProtein: EditText
    private lateinit var editTextItemFat: EditText
    private lateinit var editTextItemCarbs: EditText
    private lateinit var buttonAddItem: Button

    private var itemName: String = ""
    private var itemCalories: String = ""
    private var itemProtein: String = ""
    private var itemFat: String = ""
    private var itemCarbs: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_item, container, false);

// Initialize views
        editTextItemName = view.findViewById(R.id.editTextItemName)
        editTextItemCalories = view.findViewById(R.id.editTextItemCalories)
        editTextItemProtein = view.findViewById(R.id.editTextItemProtein)
        editTextItemFat = view.findViewById(R.id.editTextItemFat)
        editTextItemCarbs = view.findViewById(R.id.editTextItemCarbs)
        buttonAddItem = view.findViewById(R.id.buttonAddItem)


        // Set TextChangedListener for EditTexts
        editTextItemName.addTextChangedListener {
            itemName = it.toString()
        }

        editTextItemCalories.addTextChangedListener {
            itemCalories = it.toString()
        }

        editTextItemProtein.addTextChangedListener {
            itemProtein = it.toString()
        }

        editTextItemFat.addTextChangedListener {
            itemFat = it.toString()
        }

        editTextItemCarbs.addTextChangedListener {
            itemCarbs = it.toString()
        }

        // Set onClickListener for Add Item Button
        buttonAddItem.setOnClickListener {
            // Handle button click
            println("Add item button clicked")
            if (validateFields()) {
                AppDatabase.getDatabase(requireContext()).also {
                    lifecycleScope.launch {
                        it.foodItemDao().insertFoodItem(
                            name = itemName,
                            calories = itemCalories.toDouble(),
                            protein = itemProtein.toDouble(),
                            fat = itemFat.toDouble(),
                            carbs = itemCarbs.toDouble()
                        )
                    }
                }
            }
        }


        return view
    }

    private fun validateFields(): Boolean {
        // Regular expression to match only numbers
        val regex = "\\d+".toRegex()

        if (itemName.isEmpty() || itemCalories.isEmpty() || itemProtein.isEmpty() || itemFat.isEmpty() || itemCarbs.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return false
        } else if (!itemCalories.matches(regex) || !itemProtein.matches(regex) || !itemFat.matches(
                regex
            ) || !itemCarbs.matches(regex)
        ) {
            Toast.makeText(
                requireContext(),
                "Calories, Protein, Fat, and Carbs fields should contain only numbers",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else {
            return true
        }
    }


}