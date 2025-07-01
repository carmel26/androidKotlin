package com.example.notespreparation

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView // Import GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display for a more immersive experience
        setContentView(R.layout.activity_main) // Sets the layout for this activity


        val gridView: GridView = findViewById(R.id.idGrid)


        val dataItems = arrayOf(
            "Grid Item 1", "Grid Item 2", "Grid Item 3", "Grid Item 4",
            "Grid Item 5", "Grid Item 6", "Grid Item 7", "Grid Item 8",
            "Grid Item 9", "Grid Item 10", "Grid Item 11", "Grid Item 12",
            "Grid Item 13", "Grid Item 14", "Grid Item 15", "Grid Item 16",
            "Grid Item 17", "Grid Item 18", "Grid Item 19", "Grid Item 20"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            dataItems
        )

        gridView.adapter = adapter

//        // This allows you to perform an action when a user taps on an item in the grid.
//        gridView.setOnItemClickListener { parent, view, position, id ->
//            val selectedItem = parent.getItemAtPosition(position) as String
//            Toast.makeText(this, "You selected: $selectedItem", Toast.LENGTH_SHORT).show()
//        }
    }
}
