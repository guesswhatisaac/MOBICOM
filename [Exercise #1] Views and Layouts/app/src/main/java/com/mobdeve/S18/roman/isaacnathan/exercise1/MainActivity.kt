package com.mobdeve.S18.roman.isaacnathan.exercise1

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        var isFilled = false

        fab.setOnClickListener {
            Log.d(TAG, "FloatingActionButton clicked!")
            if (isFilled) {
                fab.setImageResource(R.drawable.starnotfilled) // Set to unfilled star
            } else {
                fab.setImageResource(R.drawable.starfilled) // Set to filled star
            }
            isFilled = !isFilled
        }


    }
}