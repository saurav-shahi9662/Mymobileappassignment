package com.example.finalassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// ViewModel class to manage login logic and maintain data for the UI
class LoginViewModel : ViewModel() {

    // LiveData to hold the login result (keypass)
    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> get() = _loginResult  // Publicly exposed LiveData for observing login result

    // LiveData to hold error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage  // Publicly exposed LiveData for observing errors

    // Function to perform login operation using coroutines
    fun performLogin(username: String, password: String) {
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(username, password)  // Create the login request object

                // Make the network call in the IO thread and get the response
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.authService.login(loginRequest)
                }

                // Update the LiveData with the keypass from the response
                _loginResult.value = response.keypass

            } catch (e: Exception) {
                // Update the LiveData with the error message if an exception occurs
                _errorMessage.value = e.message ?: "Unknown error"
            }
        }
    }
}
