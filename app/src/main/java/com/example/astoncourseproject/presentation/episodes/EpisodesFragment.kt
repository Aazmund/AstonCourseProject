package com.example.astoncourseproject.presentation.episodes

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
import com.example.astoncourseproject.entities.Episode
import com.example.astoncourseproject.presentation.adapters.episodes.EpisodeRecyclerAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EpisodesFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pullToRefresh = view.findViewById<SwipeRefreshLayout>(R.id.episodesRefreshLayout).apply {
            setOnRefreshListener {
                onRefresh()
                isRefreshing = false
            }
        }

        view.findViewById<RecyclerView>(R.id.episodeRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = EpisodeRecyclerAdapter(data())
        }
    }

    private fun onRefresh(){
        Toast.makeText(context, "Данные обновлены", Toast.LENGTH_SHORT).show()
    }

    private fun data(): List<Episode>{
        val data = mutableListOf<Episode>()
        repeat((0..30).count()) {
            val episode: Episode = Episode().apply {
                episodeName = "Pilot"
                episodeNumber = "S01E01"
                airDate = "December 2, 2013"
            }
            data.add(episode)
        }
        return data
    }
}