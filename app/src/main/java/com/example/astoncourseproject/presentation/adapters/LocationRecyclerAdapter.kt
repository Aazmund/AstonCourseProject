package com.example.astoncourseproject.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astoncourseproject.R
import com.example.astoncourseproject.domain.models.Location

class LocationRecyclerAdapter(
    private var locations: List<Location>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<LocationRecyclerAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_location, parent, false)
        return LocationViewHolder(itemView, onItemClicked)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(list: List<Location>) {
        locations = list
        notifyDataSetChanged()
    }

    class LocationViewHolder(
        itemView: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        private val locationName: TextView = itemView.findViewById(R.id.textViewLocationName)
        private val locationType: TextView = itemView.findViewById(R.id.textViewLocationType)
        private val locationDimension: TextView =
            itemView.findViewById(R.id.textViewLocationDimension)

        fun bind(location: Location) {
            locationName.text = location.name
            locationType.text = location.type
            locationDimension.text = location.dimension
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }
}