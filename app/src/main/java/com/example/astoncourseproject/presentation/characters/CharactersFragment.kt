package com.example.astoncourseproject.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.astoncourseproject.R
import com.example.astoncourseproject.presentation.adapters.characters.CharacterRecyclerAdapter
import com.example.astoncourseproject.entities.Character

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharactersFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pullToRefresh = view.findViewById<SwipeRefreshLayout>(R.id.characterRefreshLayout).apply {
            setOnRefreshListener {
                onRefresh()
                isRefreshing = false
            }
        }

        view.findViewById<RecyclerView>(R.id.characterRecyclerView).apply {
            layoutManager = GridLayoutManager(view.context, 2)
            val characterRecyclerAdapter = CharacterRecyclerAdapter(data()){
                position -> onItemClicked(position)
            }
            adapter = characterRecyclerAdapter
        }

    }

    private fun onRefresh(){
        Toast.makeText(context, "Данные обновлены", Toast.LENGTH_SHORT).show()
    }

    private fun data(): List<Character>{
        val data = mutableListOf<Character>()
        repeat((0..30).count()) {
            val character: Character = Character().apply {
                characterName = "qqqqqq qqqqqq"
                characterGender = "vewvewrvewrv"
                characterStatus = "wvwqvqw wqe"
                characterSpecies = "wvwrqvqrwvqwrv"
            }
            data.add(character)
        }
        return data
    }

    private fun onItemClicked(position: Int){
        val characterDetailFragment = CharacterDetailFragment()
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        val replace = transaction.replace(R.id.fragmentContainerView, characterDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}