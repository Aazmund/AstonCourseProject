package com.example.astoncourseproject.presentation.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.astoncourseproject.R
import com.example.astoncourseproject.entities.Location
import com.example.astoncourseproject.presentation.adapters.locations.LocationRecyclerAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LocationsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pullToRefresh = view.findViewById<SwipeRefreshLayout>(R.id.locationsRefreshLayout).apply {
            setOnRefreshListener {
                onRefresh()
                isRefreshing = false
            }
        }

        view.findViewById<RecyclerView>(R.id.locationRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = LocationRecyclerAdapter(data())
        }
    }

    private fun onRefresh(){
        Toast.makeText(context, "Данные обновлены", Toast.LENGTH_SHORT).show()
    }

    private fun data(): List<Location>{
        val data = mutableListOf<Location>()
        repeat((0..30).count()) {
            val location: Location = Location().apply {
                locationName = "Earth"
                locationType = "Planet"
                locationDimension = "Dimension C-137"
            }
            data.add(location)
        }
        return data
    }
}