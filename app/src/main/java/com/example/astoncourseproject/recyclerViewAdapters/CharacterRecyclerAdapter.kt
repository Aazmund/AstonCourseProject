package com.example.astoncourseproject.recyclerViewAdapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astoncourseproject.R
import com.example.astoncourseproject.entities.Character

class CharacterRecyclerAdapter(
    private var characters: List<Character>,
    private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<CharacterRecyclerAdapter.CharacterViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_character, parent, false)
        return CharacterViewHolder(itemView, onItemClicked)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterList(list: List<Character>){
        characters = list
        notifyDataSetChanged()
    }

    class CharacterViewHolder(
        itemView: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        private val characterImg: ImageView = itemView.findViewById(R.id.characterImg)
        private val characterName: TextView = itemView.findViewById(R.id.textViewCharacterName)
        private val characterSpecies: TextView = itemView.findViewById(R.id.textViewCharacterSpecies)
        private val characterGender: TextView = itemView.findViewById(R.id.textViewCharacterGender)
        private val characterStatus: TextView = itemView.findViewById(R.id.textViewCharacterStatus)

        fun bind(character: Character) {
            characterImg.setImageBitmap(character.imageBitmap)
            characterName.text = character.name
            characterSpecies.text = character.species
            characterGender.text = character.gender
            characterStatus.text = character.status
        }

        override fun onClick(view: View){
            val position = adapterPosition
            onItemClicked(position)
        }
    }
}