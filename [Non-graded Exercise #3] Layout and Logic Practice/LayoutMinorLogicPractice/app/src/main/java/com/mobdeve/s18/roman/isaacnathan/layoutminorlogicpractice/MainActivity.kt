package com.mobdeve.s18.roman.isaacnathan.layoutminorlogicpractice

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s18.roman.isaacnathan.layoutminorlogicpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.floatingActionButton5.setOnClickListener {
            Toast.makeText(this, "Adding S15...", Toast.LENGTH_SHORT).show()
        }

        binding.floatingActionButton6.setOnClickListener {
            Toast.makeText(this, "Adding S16...", Toast.LENGTH_SHORT).show()
        }


    }
}


