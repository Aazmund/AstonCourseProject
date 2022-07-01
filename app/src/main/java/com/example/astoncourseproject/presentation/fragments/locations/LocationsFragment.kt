package com.example.astoncourseproject.presentation.fragments.locations

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class LocationsFragment : Fragment() {

    private lateinit var vm: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(this, LocationVMFactory())[LocationViewModel::class.java]
        vm.update()
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

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationAdapter = LocationRecyclerAdapter(emptyList()) { position ->
            onItemClicked(position)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.locationRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = locationAdapter
        }

        vm.liveData.observe(this) {
            locationAdapter.updateAdapter(it)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
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
    }

    private fun onRefresh() {
        vm.update()
        Toast.makeText(context, "Данные обновлены", Toast.LENGTH_SHORT).show()
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