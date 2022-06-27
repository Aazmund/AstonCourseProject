package com.example.astoncourseproject.presentation.fragments.episodes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.adapters.EpisodeRecyclerAdapter
import com.example.astoncourseproject.presentation.viewmodels.EpisodeViewModel
import com.example.astoncourseproject.presentation.viewmodels.factory.EpisodeVMFactory

class EpisodesFragment : Fragment() {

    lateinit var vm: EpisodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        vm = ViewModelProvider(this, EpisodeVMFactory())[EpisodeViewModel::class.java]
        vm.update()

        val recyclerView = view.findViewById<RecyclerView>(R.id.episodeRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)

        val adapter = EpisodeRecyclerAdapter(emptyList())

        recyclerView.adapter = adapter

        vm.liveData.observe(this) {
            adapter.updateAdapter(it)
        }

        val pullToRefresh = view.findViewById<SwipeRefreshLayout>(R.id.episodesRefreshLayout).apply {
            setOnRefreshListener {
                onRefresh()
                isRefreshing = false
            }
        }
    }

    private fun onRefresh(){
        vm.update()
        Toast.makeText(context, "Данные обновлены", Toast.LENGTH_SHORT).show()
    }

//    private fun data(): List<Episode>{
//        val data = mutableListOf<Episode>()
//        repeat((0..30).count()) {
//            val episode: Episode = Episode().apply {
//                episodeName = "Pilot"
//                episodeNumber = "S01E01"
//                airDate = "December 2, 2013"
//            }
//            data.add(episode)
//        }
//        return data
//    }
}