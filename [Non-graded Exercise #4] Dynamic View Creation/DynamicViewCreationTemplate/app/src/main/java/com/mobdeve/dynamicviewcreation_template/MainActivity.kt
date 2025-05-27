package com.mobdeve.dynamicviewcreation_template

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.dynamicviewcreation_template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firstNameEtv: EditText
    private lateinit var lastNameEtv: EditText
    private lateinit var addBtn: Button
    private lateinit var namesLl: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.firstNameEtv = binding.firstNameEtv
        this.lastNameEtv = binding.lastNameEtv
        this.addBtn = binding.addBtn
        this.namesLl = binding.nameListLl

        this.addBtn.setOnClickListener {
            val firstName = this.firstNameEtv.text.toString().trim()
            val lastName = this.lastNameEtv.text.toString().trim()

            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val nameTv = TextView(this)
                nameTv.text = "$lastName, $firstName"
                this.namesLl.addView(nameTv)
            } else {
                Toast.makeText(this, "Please enter both first and last names.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}