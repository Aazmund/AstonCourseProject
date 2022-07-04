package com.example.astoncourseproject.presentation.fragments.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.astoncourseproject.MainActivity
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.adapters.CharacterRecyclerAdapter
import com.example.astoncourseproject.presentation.navigation.NavigationFragment
import com.example.astoncourseproject.presentation.viewmodels.character.CharacterViewModel
import com.example.astoncourseproject.presentation.viewmodels.character.factory.CharacterVMFactory
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class CharactersFragment : Fragment() {

    private lateinit var vm: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this, CharacterVMFactory(requireActivity().application))[CharacterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve", "InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.update()

        val characterAdapter = CharacterRecyclerAdapter(emptyList()) { position ->
            onItemClicked(position)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.characterRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = characterAdapter
        }

        vm.liveData.observe(this) {
            characterAdapter.updateAdapter(it)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    vm.addNewPage()
                }
            }
        })

        view.findViewById<SwipeRefreshLayout>(R.id.characterRefreshLayout).apply {
            setOnRefreshListener {
                onRefresh()
                isRefreshing = false
            }
        }

        view.findViewById<Button>(R.id.addFiltersButton).apply {
            setOnClickListener {
                val window = PopupWindow(view)
                val v = layoutInflater.inflate(R.layout.character_filters, null)
                window.contentView = v
                v.findViewById<Button>(R.id.applyFiltersButton).apply {
                    setOnClickListener {
                        val titles = mutableListOf<String>()
                        var search = ""
                        v.findViewById<SearchView>(R.id.searchView).apply {
                            search = this.query.toString()
                        }
                        val g1 = v.findViewById<ChipGroup>(R.id.chipGroup)
                        val g2 = v.findViewById<ChipGroup>(R.id.chipGroup2)
                        val g3 = v.findViewById<ChipGroup>(R.id.chipGroup3)
                        var ids = g1.checkedChipIds
                        ids.forEach { id ->
                            titles.add(g1.findViewById<Chip>(id).text.toString())
                        }
                        ids = g2.checkedChipIds
                        ids.forEach { id ->
                            titles.add(g2.findViewById<Chip>(id).text.toString())
                        }
                        ids = g3.checkedChipIds
                        ids.forEach { id ->
                            titles.add(g3.findViewById<Chip>(id).text.toString())
                        }
                        vm.registerFilterChanged(search, titles)
                        window.dismiss()
                    }
                }
                window.isFocusable = true
                window.width = ViewGroup.LayoutParams.MATCH_PARENT
                window.height = ViewGroup.LayoutParams.MATCH_PARENT
                window.showAtLocation(view, 1, 0, 0)
                window.update()
            }
        }
    }

    private fun onRefresh() {
        vm.update()
    }

    private fun onItemClicked(position: Int) {
        val character = vm.liveData.value?.get(position)
        val bundle = Bundle().apply {
            putString("id", character?.id)
        }
        val manager: FragmentManager = parentFragmentManager
        val characterDetailFragment = CharacterDetailFragment().apply {
            arguments = bundle
        }
        val navigationFragment: NavigationFragment =
            manager.findFragmentById(R.id.navigationFragmentContainerView) as NavigationFragment
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, characterDetailFragment)
        transaction.hide(navigationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}