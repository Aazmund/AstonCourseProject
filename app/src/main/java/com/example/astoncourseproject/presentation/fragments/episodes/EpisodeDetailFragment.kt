package com.example.astoncourseproject.presentation.fragments.episodes

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
import com.example.astoncourseproject.presentation.viewmodels.EpisodeDetailViewModel
import com.example.astoncourseproject.presentation.viewmodels.factory.EpisodeDetailVMFactory

private const val ARG_PARAM1 = "id"

class EpisodeDetailFragment : Fragment() {
    private var param1: String? = null
    private lateinit var vm: EpisodeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        vm = ViewModelProvider(this, EpisodeDetailVMFactory())[EpisodeDetailViewModel::class.java]
        vm.update(param1!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_detail, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val episodeName = view.findViewById<TextView>(R.id.episodeNameTextView)
        val episodeAirDate = view.findViewById<TextView>(R.id.episodeAirDateTextView)
        val episodeNumber = view.findViewById<TextView>(R.id.episodeNumberTextView)

        vm.liveData.observe(this) {
            episodeName.text = it[0].name
            episodeAirDate.text = it[0].air_date
            episodeNumber.text = it[0].episode
        }
    }

    override fun onDestroy() {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
        super.onDestroy()
    }
}