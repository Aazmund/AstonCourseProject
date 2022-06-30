package com.example.astoncourseproject.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astoncourseproject.R
import com.example.astoncourseproject.domain.models.Episode

class EpisodeRecyclerAdapter(
    private var episodes: List<Episode>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<EpisodeRecyclerAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_episode, parent, false)
        return EpisodeViewHolder(itemView, onItemClicked)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(list: List<Episode>) {
        episodes = list
        notifyDataSetChanged()
    }

    class EpisodeViewHolder(
        itemView: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        private val episodeName: TextView = itemView.findViewById(R.id.textViewEpisodeName)
        private val episodeNumber: TextView = itemView.findViewById(R.id.textViewEpisodeNumber)
        private val airDate: TextView = itemView.findViewById(R.id.textViewEpisodeAirDate)

        fun bind(episode: Episode) {
            episodeName.text = episode.name
            episodeNumber.text = episode.episode
            airDate.text = episode.air_date
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }
}