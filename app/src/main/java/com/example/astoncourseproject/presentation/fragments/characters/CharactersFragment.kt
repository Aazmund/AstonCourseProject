package com.example.astoncourseproject.presentation.fragments.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.adapters.CharacterRecyclerAdapter
import com.example.astoncourseproject.presentation.navigation.NavigationFragment
import com.example.astoncourseproject.presentation.viewmodels.CharacterViewModel
import com.example.astoncourseproject.presentation.viewmodels.factory.CharacterVMFactory

class CharactersFragment : Fragment() {

    private lateinit var vm: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(this, CharacterVMFactory())[CharacterViewModel::class.java]
        vm.update()

        val recyclerView = view.findViewById<RecyclerView>(R.id.characterRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)

        val adapter = CharacterRecyclerAdapter(emptyList()){
                position -> onItemClicked(position)
        }

        recyclerView.adapter = adapter

        vm.liveData.observe(this) {
            adapter.updateAdapter(it)
        }

        view.findViewById<SwipeRefreshLayout>(R.id.characterRefreshLayout).apply {
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

    private fun onItemClicked(position: Int){

        val character = vm.liveData.value?.get(position)
        val bundle = Bundle().apply {
            putString("id", character?.id)
        }
        val manager: FragmentManager = parentFragmentManager
        val characterDetailFragment = CharacterDetailFragment().apply {
            arguments = bundle
        }
        val navigationFragment: NavigationFragment = manager.findFragmentById(R.id.navigationFragmentContainerView) as NavigationFragment
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, characterDetailFragment)
        transaction.hide(navigationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}