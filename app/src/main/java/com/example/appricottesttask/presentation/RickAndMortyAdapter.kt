package com.example.appricottesttask.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appricottesttask.R
import com.example.appricottesttask.databinding.RickAndMortyItemBinding
import com.example.appricottesttask.domain.models.Characters

class RickAndMortyAdapter : PagingDataAdapter<Characters, RickAndMortyAdapter.RickAndMortyViewHolder>(DiffUtilCallBack()) {

    class DiffUtilCallBack: DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RickAndMortyItemBinding.inflate(inflater, parent, false)
        return RickAndMortyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RickAndMortyViewHolder, position: Int) {
        val character = getItem(position) ?: Characters(0, "", "", "", "")
        with(holder.binding) {
            nameText.text = character.name
            speciesText.text = character.species
            genderText.text = character.gender
            imageView.load(character.image) {
                placeholder(R.drawable.ic_image)
                error(R.drawable.ic_image)
                crossfade(true)
            }
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(character) }
            }
        }
    }

    class RickAndMortyViewHolder(val binding: RickAndMortyItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((Characters) -> Unit)? = null

    fun setOnItemClickListener(listener: (Characters) -> Unit) {
        onItemClickListener = listener
    }
}