package com.example.astoncourseproject.presentation.fragments.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astoncourseproject.MainActivity
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.adapters.EpisodeRecyclerAdapter
import com.example.astoncourseproject.presentation.fragments.episodes.EpisodeDetailFragment
import com.example.astoncourseproject.presentation.fragments.locations.LocationDetailFragment
import com.example.astoncourseproject.presentation.viewmodels.character.CharacterDetailViewModel
import com.example.astoncourseproject.presentation.viewmodels.character.factory.CharacterDetailVMFactory

private const val ARG_PARAM1 = "id"

class CharacterDetailFragment : Fragment() {
    private var param1: String? = null
    private lateinit var vm: CharacterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        vm = ViewModelProvider(
            this,
            CharacterDetailVMFactory(requireActivity().application)
        )[CharacterDetailViewModel::class.java]
        vm.update(param1!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    @SuppressLint("UseSupportActionBar", "FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterName = view.findViewById<TextView>(R.id.characterNameTextView)
        val characterStatus = view.findViewById<TextView>(R.id.characterStatusTextView)
        val characterSpecies = view.findViewById<TextView>(R.id.characterSpeciesTextView)
        val characterGender = view.findViewById<TextView>(R.id.characterGenderTextView)
        val characterImage = view.findViewById<ImageView>(R.id.characterImageView)
        val characterOrigin = view.findViewById<TextView>(R.id.characterOriginTextView).apply {
            setOnClickListener {
                originClick()
            }
        }
        val characterLocation = view.findViewById<TextView>(R.id.characterLocationTextView).apply {
            setOnClickListener {
                locationClick()
            }
        }

        val episodeAdapter = EpisodeRecyclerAdapter(emptyList()){ position ->
            onItemClicked(position)
        }

        view.findViewById<RecyclerView>(R.id.characterDetailEpisodeList).apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = episodeAdapter
        }

        vm.liveData.observe(this) {
            Glide.with(view.context).asBitmap().load(it[0].image).into(characterImage)
            characterName.text = it[0].name
            characterStatus.text = it[0].status
            characterSpecies.text = it[0].species
            characterGender.text = it[0].gender
            characterOrigin.text = it[0].origin["name"]
            characterLocation.text = it[0].location["name"]
            vm.updateEpisodes(it[0].episode)
        }

        vm.episodeLiveData.observe(this) {
            episodeAdapter.updateAdapter(it)
        }

    }

    private fun onItemClicked(position: Int){
        vm.episodeLiveData.value.apply {
            if (this != null){
                val id = this[position].id
                openEpisodeFragment(id)
            }
        }
    }

    private fun originClick(){
        vm.liveData.value.apply {
            if (this != null && this[0].origin["name"] != "unknown"){
                var originId = this[0].origin["url"]
                originId = originId?.substring(originId.lastIndexOf("/") + 1, originId.length)
                openLocationFragment(originId)
            }
        }
    }

    private fun locationClick(){
        vm.liveData.value.apply {
            if (this != null){
                var originId = this[0].location["url"]
                originId = originId?.substring(originId.lastIndexOf("/") + 1, originId.length)
                openLocationFragment(originId)
            }
        }
    }

    private fun openEpisodeFragment(id: String?){
        val bundle = Bundle().apply {
            putString("id", id)
        }
        val manager: FragmentManager = parentFragmentManager
        val episodeDetailFragment = EpisodeDetailFragment().apply {
            arguments = bundle
        }
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, episodeDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun openLocationFragment(id: String?){
        val bundle = Bundle().apply {
            putString("id", id)
        }
        val manager: FragmentManager = parentFragmentManager
        val locationDetailFragment = LocationDetailFragment().apply {
            arguments = bundle
        }
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, locationDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}