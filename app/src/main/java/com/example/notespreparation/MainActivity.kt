package com.example.notespreparation

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listview)


        val dataItems = arrayOf(
            "Item 1: Learn Kotlin",
            "Item 2: Build Android Apps",
            "Item 3: Explore UI Components",
            "Item 4: Understand Layouts",
            "Item 5: Handle User Input",
            "Item 6: Work with Data",
            "Item 7: Implement Navigation",
            "Item 8: Test Your App",
            "Item 9: Deploy to Play Store",
            "Item 10: Continuously Learn"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            dataItems
        )


        listView.adapter = adapter
//
//        // 4. (Optional) Set an item click listener
//        // This allows you to perform an action when a user taps on an item in the list.
//        listView.setOnItemClickListener { parent, view, position, id ->
//            val selectedItem = parent.getItemAtPosition(position) as String
//            Toast.makeText(this, "You selected: $selectedItem", Toast.LENGTH_SHORT).show()
//        }
    }
}
