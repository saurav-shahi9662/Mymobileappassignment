package com.example.finalassignment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardActivity : AppCompatActivity() {

    // Declare RecyclerView and Adapter for displaying entities
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EntityAdapter

   //Initializes the activity, sets up the RecyclerView, and fetches the dashboard data.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initializing RecyclerView and setting its layout manager
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve the keypass from the intent that started this activity
        val keypass = intent.getStringExtra("keypass")

        // If keypass is available, fetch the dashboard data
        if (keypass != null) {
            fetchDashboardData(keypass)
        } else {
            // If keypass is missing, show a toast message
            Toast.makeText(this, "Keypass is missing", Toast.LENGTH_SHORT).show()
        }
    }
     //Fetching the dashboard data using coroutines and Retrofit.
    //The API call runs in the background using Dispatchers.IO.

    private fun fetchDashboardData(keypass: String) {
        // Using lifecycleScope to launch a coroutine tied to the activity's lifecycle
        lifecycleScope.launch {
            try {
                // Make the network request in the IO thread (background thread)
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.authService.getDashboard(keypass)
                }

                // Call the function to display the data in the RecyclerView
                displayData(response.entities)

            } catch (e: Exception) {
                // If an error occurs during the API call, show a toast message with the error
                Toast.makeText(this@DashboardActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
     //Displaying the list of entities in the RecyclerView by setting up the adapter.

    private fun displayData(entities: List<Entity>) {
        // Initialize the adapter with the list of entities and set it to the RecyclerView
        adapter = EntityAdapter(entities) { entity ->
            // Handle the click on a RecyclerView item if needed
        }
        recyclerView.adapter = adapter
    }
}
