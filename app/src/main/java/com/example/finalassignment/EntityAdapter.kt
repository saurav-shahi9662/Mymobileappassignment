package com.example.finalassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class to bind Entity data to the RecyclerView
class EntityAdapter(
    private val entityList: List<Entity>,  // List of entities to display
    private val onItemClick: (Entity) -> Unit  // Lambda function to handle item clicks
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    // ViewHolder class to hold references to the views for each list item
    class EntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProperty1: TextView = view.findViewById(R.id.tvProperty1)  // TextView for property1
        val tvProperty2: TextView = view.findViewById(R.id.tvProperty2)  // TextView for property2
    }

    // Called to create a new ViewHolder for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)  // Inflate the item layout and return a ViewHolder
    }

    // Called to bind the data to the views of each ViewHolder
    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entityList[position]  // Get the entity at the current position
        holder.tvProperty1.text = entity.property1  // Set property1 text
        holder.tvProperty2.text = entity.property2  // Set property2 text

        // Set a click listener to handle item click events
        holder.itemView.setOnClickListener {
            onItemClick(entity)  // Trigger the click handler with the clicked entity
        }
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int = entityList.size
}
