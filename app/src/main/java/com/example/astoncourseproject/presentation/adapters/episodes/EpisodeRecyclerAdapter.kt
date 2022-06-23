package com.example.astoncourseproject.presentation.adapters.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astoncourseproject.R
import com.example.astoncourseproject.entities.Episode

class EpisodeRecyclerAdapter(private val episodes: List<Episode>):
    RecyclerView.Adapter<EpisodeRecyclerAdapter.EpisodeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_episode, parent, false)
        return EpisodeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val episodeName: TextView = itemView.findViewById(R.id.textViewEpisodeName)
        private val episodeNumber: TextView = itemView.findViewById(R.id.textViewEpisodeNumber)
        private val airDate: TextView = itemView.findViewById(R.id.textViewEpisodeAirDate)

        fun bind(episode: Episode) {
            episodeName.text = episode.episodeName
            episodeNumber.text = episode.episodeNumber
            airDate.text = episode.airDate
        }
    }
}