package com.example.finalassignment

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    //Authenticating user by sending a POST request to the appropriate endpoint.

    @POST("footscray/auth") // This can be "/sydney/auth" or "/ort/auth" based on class location.
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse


    //Fetches the dashboard data by sending a GET request to the '/dashboard/{keypass}' endpoint.
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): DashboardResponse
}
