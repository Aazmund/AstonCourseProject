package com.example.astoncourseproject.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astoncourseproject.R
import com.example.astoncourseproject.entities.Location

class LocationRecyclerAdapter(private val locations: List<Location>):
    RecyclerView.Adapter<LocationRecyclerAdapter.LocationViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_location, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val locationName: TextView = itemView.findViewById(R.id.textViewLocationName)
        private val locationType: TextView = itemView.findViewById(R.id.textViewLocationType)
        private val locationDimension: TextView = itemView.findViewById(R.id.textViewLocationDimension)

        fun bind(location: Location) {
            locationName.text = location.locationName
            locationType.text = location.locationType
            locationDimension.text = location.locationDimension
        }
    }
}