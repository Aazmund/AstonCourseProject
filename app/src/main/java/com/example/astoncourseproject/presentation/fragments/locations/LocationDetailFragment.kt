package com.example.astoncourseproject.presentation.fragments.locations

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.MainActivity
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.viewmodels.LocationDetailViewModel
import com.example.astoncourseproject.presentation.viewmodels.factory.LocationDetailVMFactory

private const val ARG_PARAM1 = "id"

class LocationDetailFragment : Fragment() {

    private var param1: String? = null
    private lateinit var vm: LocationDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        vm = ViewModelProvider(this, LocationDetailVMFactory())[LocationDetailViewModel::class.java]
        vm.update(param1!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_detail, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationName = view.findViewById<TextView>(R.id.locationNameTextView)
        val locationType = view.findViewById<TextView>(R.id.locationTypeTextView)
        val locationDimension = view.findViewById<TextView>(R.id.locationDimensionTextView)

        vm.liveData.observe(this) {
            locationName.text = it[0].name
            locationType.text = it[0].type
            locationDimension.text = it[0].dimension
        }
    }

    override fun onDestroy() {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
        super.onDestroy()
    }
}