package com.example.finalassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DashboardViewModel : ViewModel() {

    // LiveData to hold the list of entities
    private val _entities = MutableLiveData<List<Entity>>()
    val entities: LiveData<List<Entity>> get() = _entities

    // LiveData to hold error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Function to fetch dashboard data using coroutines
    fun fetchDashboardData(keypass: String) {
        viewModelScope.launch { // Launch a coroutine tied to the ViewModel's lifecycle
            try {
                // Making the API call on the IO thread (background thread)
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.authService.getDashboard(keypass)
                }
                _entities.value = response.entities // Update LiveData with the list of entities
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown error" // Update LiveData with error message
            }
        }
    }
}
