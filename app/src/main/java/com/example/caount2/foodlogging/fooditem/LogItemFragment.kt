package com.example.caount2.foodlogging.fooditem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caount2.R
import com.example.caount2.appdb.databseconfig.AppDatabase
import kotlinx.coroutines.launch

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
                }
            }
        }


        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        foodItemAdapter = FoodItemAdapter(foodItemList) { foodItemCell ->
            onFoodItemClicked(foodItemCell)
        }
        recyclerView.adapter = foodItemAdapter


        return view
    }

    private fun onFoodItemClicked(foodItem: FoodItemCell) {

    }


}