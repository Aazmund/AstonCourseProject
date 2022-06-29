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
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.adapters.LocationRecyclerAdapter
import com.example.astoncourseproject.presentation.navigation.NavigationFragment
import com.example.astoncourseproject.presentation.viewmodels.LocationViewModel
import com.example.astoncourseproject.presentation.viewmodels.factory.LocationVMFactory

class LocationsFragment : Fragment() {

    private lateinit var vm: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(this, LocationVMFactory())[LocationViewModel::class.java]
        vm.update()

        val recyclerView = view.findViewById<RecyclerView>(R.id.locationRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)

        val adapter = LocationRecyclerAdapter(emptyList()){
                position -> onItemClicked(position)
        }

        recyclerView.adapter = adapter

        vm.liveData.observe(this){
            adapter.updateAdapter(it)
        }

        view.findViewById<SwipeRefreshLayout>(R.id.locationsRefreshLayout).apply {
            setOnRefreshListener {
                onRefresh()
                isRefreshing = false
            }
        }
    }

    private fun onRefresh(){
        Toast.makeText(context, "Данные обновлены", Toast.LENGTH_SHORT).show()
    }

    private fun onItemClicked(position: Int){
        val manager: FragmentManager = parentFragmentManager
        val locationDetailFragment = LocationDetailFragment()
        val navigationFragment: NavigationFragment = manager.findFragmentById(R.id.navigationFragmentContainerView) as NavigationFragment
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, locationDetailFragment)
        transaction.hide(navigationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}