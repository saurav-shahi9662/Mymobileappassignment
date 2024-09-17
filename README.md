# Saurav Shahi s8070345's Final Assignment - Android Application

This is my Android application developed as part of the NIT3213 Final Assignment. The project demonstrates API integration, user interface design, and Android development best practices.

## Table of Contents
- [Overview]
- [Features]
- [API Details]
- [Screens]
- [Technical Details]
- [Installation]
- [Usage]


## Overview
This project consists of three main screens:
1. Login Screen: Allows users to log in using their username and password.
2. Dashboard Screen: Displays a list of entities retrieved from the backend API.
3. Details Screen: Displays detailed information about a selected entity.(not included in this project)

The application interacts with a backend API for user authentication and data retrieval.

## Features
- User authentication via a backend API.
- Fetching and displaying data using `Recycler View`.
- User-friendly interface with proper error handling.
- Use of Kotlin Coroutines for asynchronous operations.
- Navigation between activities and passing data between screens.

## API Details
The application communicates with the following API endpoints:

1. Login Endpoint:
   - URL: `/footscray/auth`
   - Method: `POST`
   - Request Body:
     ```json
     {
       "username": "YourFirstName",
       "password": "sYourStudentID"
     }
     ```
   - Response:
     ```json
     {
       "keypass": "topicName"
     }
     ```

2. Dashboard Endpoint:
   - URL: `/dashboard/{keypass}`
   - Method: `GET`
   - Response:
     ```json
     {
       "entities": [
         {
           "property1": "value1",
           "property2": "value2",
           "description": "Detailed description"
         },
         ...
       ],
       "entityTotal": 7
     }
     ```

## Screens

### 1. Login Screen
- Allows users to enter their username (first name) and password (student ID).
- On successful login, it navigates to the Dashboard Screen.

### 2. Dashboard Screen
- Displays a list of entities in a `Recycler View`.
- Each entity shows a summary of its properties.
- Clicking on an entity navigates to the Details Screen.

### 3. Details Screen
- Displays detailed information about the selected entity, including its description.(not included)

## Technical Details

### Libraries and Tools:
- Retrofit: For API requests and data parsing.
- Kotlin Coroutines: For managing background operations.
- LiveData and ViewModel: For maintaining data and managing UI-related data in a lifecycle-conscious way.
- RecyclerView: For displaying lists of data efficiently.
- Material Design Components: For styling and user interface elements.

### Project Structure:
The app follows the MVVM (Model-View-ViewModel) architecture for clear separation of concerns and better maintainability. Here’s a basic structure:
- `Activities`: Contains the UI and user interaction logic.
- `ViewModels`: Handles the business logic and communicates with the API.
- `RetrofitInstance`: Manages API requests and endpoints.
- `Adapters`: For handling the data displayed in RecyclerViews.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/myfinalassignment.git
   ```
2. Open the project in Android Studio.
3. Sync the Gradle files and resolve any dependencies.
4. Build and run the application on an emulator or device.

## Usage

1. Login: Enter your username and password to authenticate.
2. Dashboard: Browse the list of entities fetched from the API.
3. Details: View detailed information about any entity by clicking on it.(not included)

## Dependencies

- Retrofit: `com.squareup.retrofit2:retrofit:2.9.0`
- Gson Converter for Retrofit: `com.squareup.retrofit2:converter-gson:2.9.0`
- Kotlin Coroutines: `org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0`
- AndroidX Libraries: `androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1`, `androidx.lifecycle:lifecycle-livedata-ktx:2.5.1`
- Material Design Components: `com.google.android.material:material:1.4.0`
