package com.example.astoncourseproject.presentation.fragments.locations

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.astoncourseproject.MainActivity
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.adapters.LocationRecyclerAdapter
import com.example.astoncourseproject.presentation.navigation.NavigationFragment
import com.example.astoncourseproject.presentation.viewmodels.location.LocationViewModel
import com.example.astoncourseproject.presentation.viewmodels.location.factory.LocationVMFactory
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class LocationsFragment : Fragment() {

    private lateinit var vm: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(this, LocationVMFactory(requireActivity().application))[LocationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve", "InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.update()
        val progressBar = view.findViewById<ProgressBar>(R.id.locationProgressBar)

        val locationAdapter = LocationRecyclerAdapter(emptyList()) { position ->
            onItemClicked(position)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.locationRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = locationAdapter
        }

        vm.liveData.observe(this) {
            progressBar.visibility = ProgressBar.VISIBLE
            locationAdapter.updateAdapter(it)
            progressBar.visibility = ProgressBar.INVISIBLE
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && vm.liveData.value?.size!! >= 20) {
                    vm.addNewPage()
                }
            }
        })

        view.findViewById<SwipeRefreshLayout>(R.id.locationsRefreshLayout).apply {
            setOnRefreshListener {
                onRefresh()
                isRefreshing = false
            }
        }

        view.findViewById<Button>(R.id.addLocationFiltersButton).apply {
            setOnClickListener {
                val window = PopupWindow(view)
                val v = layoutInflater.inflate(R.layout.location_filters, null)
                window.contentView = v
                v.findViewById<Button>(R.id.applyFiltersButton).apply {
                    setOnClickListener {
                        val titles = mutableMapOf<String, String>()
                        val g1 = v.findViewById<ChipGroup>(R.id.chipGroup)
                        v.findViewById<SearchView>(R.id.searchView).apply {
                            titles["name"] = this.query.toString()
                        }
                        var ids = g1.checkedChipIds
                        ids.forEach { id ->
                            titles["type"] = g1.findViewById<Chip>(id).text.toString()
                        }
                        vm.registerFilterChanged(titles)
                        window.dismiss()
                    }
                }
                window.isFocusable = true
                window.width = ViewGroup.LayoutParams.MATCH_PARENT
                window.height = ViewGroup.LayoutParams.MATCH_PARENT
                window.showAtLocation(view, 1, 0, 0)
                window.update()
            }
        }
    }

    private fun onRefresh() {
        vm.update()
    }

    private fun onItemClicked(position: Int) {
        val location = vm.liveData.value?.get(position)
        val bundle = Bundle().apply {
            putString("id", location?.id)
        }
        val manager: FragmentManager = parentFragmentManager
        val locationDetailFragment = LocationDetailFragment().apply {
            arguments = bundle
        }
        val navigationFragment: NavigationFragment =
            manager.findFragmentById(R.id.navigationFragmentContainerView) as NavigationFragment
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, locationDetailFragment)
        transaction.hide(navigationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}