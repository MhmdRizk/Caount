package com.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.caount.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        setupUIBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setupUIBinding() {
        binding?.progressButton?.setOnClickListener {
            //TODO:- add progress fragment
        }

        binding?.addMealButton?.setOnClickListener {
         //TODO:- add meal button fragment
        }
    }

}