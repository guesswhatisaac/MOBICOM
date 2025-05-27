package com.example.helloworldplusplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.text
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworldplusplus.ui.theme.HelloWorldPlusplusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main)

        val inputET = findViewById<EditText>(R.id.inputET)
        val displayBTN = findViewById<Button>(R.id.displayBTN)
        val displayTV = findViewById<TextView>(R.id.displayTV)

        displayBTN.setOnClickListener {
            displayTV.text = "Hello, ${inputET.text.toString()}"
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldPlusplusTheme {
        Greeting("Android")
    }
}