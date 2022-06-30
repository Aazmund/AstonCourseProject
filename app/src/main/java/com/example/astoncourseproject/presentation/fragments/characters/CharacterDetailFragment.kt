package com.example.astoncourseproject.presentation.fragments.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.astoncourseproject.MainActivity
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.viewmodels.CharacterDetailViewModel
import com.example.astoncourseproject.presentation.viewmodels.factory.CharacterDetailVMFactory

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
            CharacterDetailVMFactory()
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

        vm.liveData.observe(this) {
            Glide.with(view.context).asBitmap().load(it[0].image).into(characterImage)
            characterName.text = it[0].name
            characterStatus.text = it[0].status
            characterSpecies.text = it[0].species
            characterGender.text = it[0].gender
        }

    }

    override fun onDestroy() {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
        super.onDestroy()
    }
}