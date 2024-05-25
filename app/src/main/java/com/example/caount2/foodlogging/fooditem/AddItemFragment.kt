package com.example.caount2.foodlogging.fooditem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.caount2.R
import com.example.caount2.appdb.daos.FoodItemDao
import com.example.caount2.appdb.databseconfig.AppDatabase
import kotlinx.coroutines.launch


class AddItemFragment(private val foodItemDao: FoodItemDao) : Fragment() {


    private lateinit var textViewItemName: TextView
    private lateinit var textViewItemCalories: TextView
    private lateinit var textViewItemProtein: TextView
    private lateinit var textViewItemFat: TextView
    private lateinit var textViewItemCarbs: TextView
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
        textViewItemName = view.findViewById(R.id.textViewItemName)
        textViewItemCalories = view.findViewById(R.id.textViewItemCalories)
        textViewItemProtein = view.findViewById(R.id.textViewItemProtein)
        textViewItemFat = view.findViewById(R.id.textViewItemFat)
        textViewItemCarbs = view.findViewById(R.id.textViewItemCarbs)
        buttonAddItem = view.findViewById(R.id.buttonAddItem)


        // Set onClickListener for TextViews
        textViewItemName.setOnClickListener {
            println("TextView Item Name clicked")
            itemName = textViewItemName.text.toString()
        }
        textViewItemCalories.setOnClickListener {
            println("TextView Item Calories clicked")
            itemCalories = textViewItemCalories.text.toString()
        }
        textViewItemProtein.setOnClickListener {
            println("TextView Item Protein clicked")
            itemProtein = textViewItemProtein.text.toString()
        }
        textViewItemFat.setOnClickListener {
            println("TextView Item Fat clicked")
            itemFat = textViewItemFat.text.toString()
        }
        textViewItemCarbs.setOnClickListener {
            println("TextView Item Carbs clicked")
            itemCarbs = textViewItemCarbs.text.toString()
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