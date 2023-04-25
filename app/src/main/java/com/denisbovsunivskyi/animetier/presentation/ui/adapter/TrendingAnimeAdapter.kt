package com.denisbovsunivskyi.animetier.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.denisbovsunivskyi.animetier.data.models.anime.tranding.AnimeDto
import com.denisbovsunivskyi.animetier.databinding.ItemHorizontalAnimeBinding


class TrendingAnimeAdapter : RecyclerView.Adapter<TrendingAnimeAdapter.AnimeViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<AnimeDto>() {
        override fun areItemsTheSame(
            oldItem: AnimeDto,
            newItem: AnimeDto
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AnimeDto,
            newItem: AnimeDto
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding =
            ItemHorizontalAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = differ.currentList[position]
        holder.bind(anime)
    }

    inner class AnimeViewHolder(val binding: ItemHorizontalAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: AnimeDto) {
            binding.model = anime
            binding.root.setOnClickListener {
                onClickListener?.let {
                    it(anime)
                }
            }
        }
    }

    private var onClickListener: ((AnimeDto) -> Unit)? = null

    fun setOnClickListener(listener: (AnimeDto) -> Unit) {
        onClickListener = listener
    }

}
