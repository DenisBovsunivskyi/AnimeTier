package com.denisbovsunivskyi.animetier.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.denisbovsunivskyi.animetier.databinding.ItemVerticalGridAnimeBinding
import com.denisbovsunivskyi.animetier.domain.models.anime.AnimeModel


class AllAnimeAdapter : RecyclerView.Adapter<AllAnimeAdapter.AnimeViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<AnimeModel>() {
        override fun areItemsTheSame(
            oldItem: AnimeModel,
            newItem: AnimeModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AnimeModel,
            newItem: AnimeModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding =
            ItemVerticalGridAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = differ.currentList[position]
        holder.bind(anime)
    }

    inner class AnimeViewHolder(val binding: ItemVerticalGridAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: AnimeModel) {
            binding.model = anime
            binding.root.setOnClickListener {
                onClickListener?.let {
                    it(anime)
                }
            }
        }
    }

    private var onClickListener: ((AnimeModel) -> Unit)? = null

    fun setOnClickListener(listener: (AnimeModel) -> Unit) {
        onClickListener = listener
    }

}
