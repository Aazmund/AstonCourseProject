package com.example.astoncourseproject.presentation.fragments.episodes

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
import com.example.astoncourseproject.presentation.adapters.EpisodeRecyclerAdapter
import com.example.astoncourseproject.presentation.navigation.NavigationFragment
import com.example.astoncourseproject.presentation.viewmodels.episode.EpisodeViewModel
import com.example.astoncourseproject.presentation.viewmodels.episode.factory.EpisodeVMFactory

class EpisodesFragment : Fragment() {

    private lateinit var vm: EpisodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(this, EpisodeVMFactory())[EpisodeViewModel::class.java]
        vm.update()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val episodeAdapter = EpisodeRecyclerAdapter(emptyList()) { position ->
            onItemClicked(position)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.episodeRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = episodeAdapter
        }

        vm.liveData.observe(this) {
            episodeAdapter.updateAdapter(it)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    vm.addNewPage()
                }
            }
        })

        view.findViewById<SwipeRefreshLayout>(R.id.episodesRefreshLayout).apply {
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

        val episode = vm.liveData.value?.get(position)
        val bundle = Bundle().apply {
            putString("id", episode?.id)
        }
        val manager: FragmentManager = parentFragmentManager
        val episodeDetailFragment = EpisodeDetailFragment().apply {
            arguments = bundle
        }
        val navigationFragment: NavigationFragment =
            manager.findFragmentById(R.id.navigationFragmentContainerView) as NavigationFragment
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, episodeDetailFragment)
        transaction.hide(navigationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}