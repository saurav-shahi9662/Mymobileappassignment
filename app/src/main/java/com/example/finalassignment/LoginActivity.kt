package com.example.finalassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

// Activity for handling the user login functionality
class LoginActivity : AppCompatActivity() {

    // Declare UI components (EditText for username and password, Button for login, and TextView for error message)
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvError: TextView

    // Called when the activity is created (initialization)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize the UI components
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvError = findViewById(R.id.tvError)

        // Set an onClickListener for the login button
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()  // Get the username input
            val password = etPassword.text.toString()  // Get the password input

            // Validate input fields (ensure both username and password are filled)
            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Launch a coroutine to handle the login process asynchronously
                lifecycleScope.launch {
                    performLogin(username, password)  // Call the login function
                }
            } else {
                // Display an error message if any field is empty
                tvError.text = "Please fill in all fields"
                tvError.visibility = TextView.VISIBLE
            }
        }
    }

    // A suspend function to perform the login operation
    private suspend fun performLogin(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)  // Create the login request object

        try {
            // Make the network call in the IO thread (background) and get the login response
            val response = withContext(Dispatchers.IO) {
                RetrofitInstance.authService.login(loginRequest)
            }

            val keypass = response.keypass  // Get the keypass from the response
            // Navigate to DashboardActivity and pass the keypass to it
            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
            intent.putExtra("keypass", keypass)
            startActivity(intent)
            finish()  // Finish the LoginActivity so the user can't go back to it

        } catch (e: Exception) {
            // Check if the exception is an HttpException and display a user-friendly message for HTTP 400 errors
            if (e is HttpException && e.code() == 400) {
                tvError.text = "Invalid username or password"  // Show a friendly message for HTTP 400
            } else {
                // Handle any other exceptions that occur during the login process
                tvError.text = "Error: ${e.message}"
            }
            tvError.visibility = TextView.VISIBLE
        }
    }
}
