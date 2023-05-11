package com.denisbovsunivskyi.animetier.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.denisbovsunivskyi.animetier.databinding.ItemVerticalGridAnimeBinding
import com.denisbovsunivskyi.animetier.presentation.model.anime.AnimeListUi


class AllAnimeAdapter : PagingDataAdapter<AnimeListUi, AllAnimeAdapter.AnimeViewHolder>(AnimeSearchDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding =
            ItemVerticalGridAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class AnimeViewHolder(val binding: ItemVerticalGridAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: AnimeListUi) {
            binding.model = anime
            binding.root.setOnClickListener {
                onClickListener?.let {
                    it(anime)
                }
            }
        }
    }

    private var onClickListener: ((AnimeListUi) -> Unit)? = null

    fun setOnClickListener(listener: (AnimeListUi) -> Unit) {
        onClickListener = listener
    }

}
class AnimeSearchDiffCallBack : DiffUtil.ItemCallback<AnimeListUi>() {

    override fun areItemsTheSame(
        oldItem: AnimeListUi,
        newItem: AnimeListUi
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AnimeListUi,
        newItem: AnimeListUi
    ): Boolean {
        return oldItem == newItem
    }
}