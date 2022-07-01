package com.example.astoncourseproject.presentation.fragments.locations

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astoncourseproject.MainActivity
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.adapters.CharacterRecyclerAdapter
import com.example.astoncourseproject.presentation.fragments.characters.CharacterDetailFragment
import com.example.astoncourseproject.presentation.viewmodels.location.LocationDetailViewModel
import com.example.astoncourseproject.presentation.viewmodels.location.factory.LocationDetailVMFactory

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

        val characterAdapter = CharacterRecyclerAdapter(emptyList()){ position ->
            onItemClicked(position)
        }

        view.findViewById<RecyclerView>(R.id.locationDetailCharacterList).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = characterAdapter
        }

        vm.liveData.observe(this) {
            locationName.text = it[0].name
            locationType.text = it[0].type
            locationDimension.text = it[0].dimension
            vm.updateCharacter(it[0].residents)
        }

        vm.characterLiveData.observe(this) {
            characterAdapter.updateAdapter(it)
        }
    }

    private fun onItemClicked(position: Int){
        vm.characterLiveData.value.apply {
            if (this != null){
                val id = this[position].id
                openCharacterFragment(id)
            }
        }
    }

    private fun openCharacterFragment(id: String){
        val bundle = Bundle().apply {
            putString("id", id)
        }
        val manager: FragmentManager = parentFragmentManager
        val characterDetailFragment = CharacterDetailFragment().apply {
            arguments = bundle
        }
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, characterDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}