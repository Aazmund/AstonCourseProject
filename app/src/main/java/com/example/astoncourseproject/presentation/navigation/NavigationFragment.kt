package com.example.astoncourseproject.presentation.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.fragments.characters.CharactersFragment
import com.example.astoncourseproject.presentation.fragments.episodes.EpisodesFragment
import com.example.astoncourseproject.presentation.fragments.locations.LocationsFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NavigationFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var state = "character"

        view.findViewById<FrameLayout>(R.id.characterNavigationFrame).apply {
            setOnClickListener {
                if (state != "character"){
                    state = "character"
                    val charactersFragment = CharactersFragment()
                    val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, charactersFragment)
                    transaction.commit()
                }
            }
        }

        view.findViewById<FrameLayout>(R.id.locationsNavigationFrame).apply {
            setOnClickListener {
                if (state != "locations"){
                    state = "locations"
                    val locationsFragment = LocationsFragment()
                    val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, locationsFragment)
                    transaction.commit()
                }
            }
        }

        view.findViewById<FrameLayout>(R.id.episodesNavigationFrame).apply {
            setOnClickListener {
                if (state != "episodes"){
                    state = "episodes"
                    val episodesFragment = EpisodesFragment()
                    val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainerView, episodesFragment)
                    transaction.commit()
                }
            }
        }
    }
}