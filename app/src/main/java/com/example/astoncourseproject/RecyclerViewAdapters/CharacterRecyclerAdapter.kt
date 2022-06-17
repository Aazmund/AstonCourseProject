package com.example.astoncourseproject.RecyclerViewAdapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astoncourseproject.R
import com.example.astoncourseproject.entities.Character

class CharacterRecyclerAdapter(private val characters: List<Character>):
    RecyclerView.Adapter<CharacterRecyclerAdapter.CharacterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val characterImg: ImageView = itemView.findViewById(R.id.characterImg)
        private val characterName: TextView = itemView.findViewById(R.id.textViewCharacterName)
        private val characterSpecies: TextView = itemView.findViewById(R.id.textViewCharacterSpecies)
        private val characterGender: TextView = itemView.findViewById(R.id.textViewCharacterGender)
        private val characterStatus: TextView = itemView.findViewById(R.id.textViewCharacterStatus)

        fun bind(character: Character) {
            characterImg.setImageBitmap(character.characterImg)
            characterName.text = character.characterName
            characterSpecies.text = character.characterSpecies
            characterGender.text = character.characterGender
            characterStatus.text = character.characterStatus
        }
    }

}