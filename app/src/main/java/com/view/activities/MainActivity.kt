package com.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.caount.R
import com.view.fragments.HomeFragment
import com.view.fragments.ProgressFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.fragment_container_view)

            }
        }
    }

// RIZK:- used to replace fragment
//    fun presentHomeFragment() {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentManager.commit {
//            setReorderingAllowed(true)
//            replace<HomeFragment>(R.id.fragment_container_view)
//        }
//    }


}