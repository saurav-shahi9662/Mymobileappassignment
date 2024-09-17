package com.example.finalassignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton object to create and manage the Retrofit instance
object RetrofitInstance {
    // Base URL of the API
    private const val BASE_URL = "https://vu-nit3213-api.onrender.com/"

    // Lazy initialization of the Retrofit instance
    val retrofit: Retrofit by lazy {
        // Create the Retrofit instance using the builder pattern
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Set the base URL for the API
            .addConverterFactory(GsonConverterFactory.create())  // Add Gson converter for JSON parsing
            .build()  // Build the Retrofit instance
    }

    // Lazy initialization of the AuthService interface
    val authService: AuthService by lazy {
        // Create the AuthService interface implementation using Retrofit
        retrofit.create(AuthService::class.java)
    }
}
