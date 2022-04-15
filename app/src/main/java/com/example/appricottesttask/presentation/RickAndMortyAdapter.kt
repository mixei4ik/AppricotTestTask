package com.example.appricottesttask.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appricottesttask.R
import com.example.appricottesttask.databinding.RickAndMortyItemBinding
import com.example.appricottesttask.domain.models.Characters

class RickAndMortyAdapter : RecyclerView.Adapter<RickAndMortyAdapter.RickAndMortyViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.status == newItem.status &&
                    oldItem.species == newItem.species &&
                    oldItem.gender == newItem.gender &&
                    oldItem.image == newItem.image &&
                    oldItem.amountEpisode == newItem.amountEpisode &&
                    oldItem.location == newItem.location
        }

    }

    private val diff = AsyncListDiffer(this, diffCallback)

    var characters: List<Characters>
        get() = diff.currentList
        set(value) = diff.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RickAndMortyItemBinding.inflate(inflater, parent, false)
        return RickAndMortyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RickAndMortyViewHolder, position: Int) {
        val character = characters[position]
        with(holder.binding) {
            nameText.text = character.name
            speciesText.text = character.species
            genderText.text = character.gender
            imageView.load(character.image) {
                placeholder(R.drawable.ic_image)
                error(R.drawable.ic_image)
                crossfade(true)
            }
        }
    }

    override fun getItemCount() = characters.size

    class RickAndMortyViewHolder(val binding: RickAndMortyItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}