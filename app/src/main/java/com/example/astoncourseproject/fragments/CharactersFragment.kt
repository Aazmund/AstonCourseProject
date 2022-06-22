package com.example.astoncourseproject.fragments

import android.annotation.SuppressLint
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
import com.example.astoncourseproject.recyclerViewAdapters.CharacterRecyclerAdapter
import com.example.astoncourseproject.entities.CharacterResponse
import com.example.astoncourseproject.network.Common
import com.example.astoncourseproject.utils.ImageLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharactersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: CharacterRecyclerAdapter

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.characterRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        recyclerAdapter = CharacterRecyclerAdapter(emptyList()){ position -> onItemClicked() }
        recyclerView.adapter = recyclerAdapter

        getAllCharacters(view)

        view.findViewById<SwipeRefreshLayout>(R.id.characterRefreshLayout).apply {
            setOnRefreshListener {
                onRefresh(view)
                isRefreshing = false
            }
        }
    }

    private fun getAllCharacters(view: View){
        val services = Common.retrofitService
        services.getCharacterList().enqueue(object : Callback<CharacterResponse> {

            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                val list = response.body()?.results
                val imageLoader = ImageLoader()
                if (list != null) {
                    for (character in list){
                        character.imageBitmap = imageLoader.convert(character.image, view.context)
                    }
                    recyclerAdapter.setCharacterList(list)
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
            }
        })
    }

    private fun onRefresh(view: View){
        getAllCharacters(view)
        Toast.makeText(context, "Данные обновлены", Toast.LENGTH_SHORT).show()
    }

    private fun onItemClicked() {
        val characterDetailFragment = CharacterDetailFragment()
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, characterDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}