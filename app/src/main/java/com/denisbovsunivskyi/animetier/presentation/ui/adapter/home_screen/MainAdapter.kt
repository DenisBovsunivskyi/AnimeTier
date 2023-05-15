package com.denisbovsunivskyi.animetier.presentation.ui.adapter.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.databinding.ItemHorizontalListMainRecyclerBinding
import com.denisbovsunivskyi.animetier.databinding.ItemVerticalListMainRecyclerBinding
import com.denisbovsunivskyi.animetier.presentation.model.DataItemType
import com.denisbovsunivskyi.animetier.presentation.model.MainAnimeData

class MainAdapter : ListAdapter<MainAnimeData, MainAnimeViewHolder>(AnimeMainDiffCallBack()) {

    var itemClickListener: ((view: View, item: MainAnimeData, position: Int) -> Unit)? = null
    var titleClickListener: ((view: View, item: DataItemType, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAnimeViewHolder {
        return when (viewType) {
            R.layout.item_vertical_list_main_recycler -> MainAnimeViewHolder.VerticalAnimeViewHolder(
                ItemVerticalListMainRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            R.layout.item_horizontal_list_main_recycler -> MainAnimeViewHolder.HorizontalViewHolder(
                ItemHorizontalListMainRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: MainAnimeViewHolder, position: Int) {

        holder.itemClickListener = itemClickListener
        holder.titleClickListener = titleClickListener

        val item = getItem(position)
        when (holder) {
            is MainAnimeViewHolder.VerticalAnimeViewHolder -> holder.bind(item as MainAnimeData)
            is MainAnimeViewHolder.HorizontalViewHolder -> holder.bind(item as MainAnimeData)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            DataItemType.HORIZONTAL_LIST ->
                R.layout.item_horizontal_list_main_recycler
            else ->
                R.layout.item_vertical_list_main_recycler
        }
    }



    class AnimeMainDiffCallBack : DiffUtil.ItemCallback<MainAnimeData>() {

        override fun areItemsTheSame(
            oldItem: MainAnimeData,
            newItem: MainAnimeData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MainAnimeData,
            newItem: MainAnimeData
        ): Boolean {
            return oldItem.animeList == newItem.animeList
        }
    }

}