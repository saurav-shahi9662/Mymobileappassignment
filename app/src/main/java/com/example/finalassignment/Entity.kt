package com.example.finalassignment

import java.io.Serializable

// Data class representing an Entity, which implements Serializable
data class Entity(
    val property1: String,     // Represents the first property of the entity
    val property2: String,     // Represents the second property of the entity
    val description: String    // A detailed description of the entity
) : Serializable  // Makes the class Serializable so it can be passed between activities
