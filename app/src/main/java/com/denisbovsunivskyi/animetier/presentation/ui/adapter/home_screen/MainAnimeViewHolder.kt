package com.denisbovsunivskyi.animetier.presentation.ui.adapter.home_screen

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.databinding.ItemHorizontalListMainRecyclerBinding
import com.denisbovsunivskyi.animetier.databinding.ItemVerticalListMainRecyclerBinding
import com.denisbovsunivskyi.animetier.presentation.model.DataItemType
import com.denisbovsunivskyi.animetier.presentation.model.MainAnimeData
import com.denisbovsunivskyi.animetier.presentation.utils.MarginHorizontalItemDecoration
import com.denisbovsunivskyi.animetier.presentation.utils.MarginVerticalItemDecoration

sealed class MainAnimeViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: MainAnimeData, position: Int) -> Unit)? = null
    var titleClickListener: ((view: View, type: DataItemType, position: Int) -> Unit)? = null


    class VerticalAnimeViewHolder(private val binding: ItemVerticalListMainRecyclerBinding) :
        MainAnimeViewHolder(binding) {
        private val childMembersAdapter by lazy { VerticalGridAnimeAdapter() }

        init {
            binding.verticalRv.addItemDecoration(
                MarginVerticalItemDecoration(
                    bottomSize = binding.verticalRv.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin_bottom
                    ),
                    leftSize = binding.verticalRv.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin
                    ),
                    rightSize = binding.verticalRv.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin
                    )

                )
            )
            binding.verticalRv.layoutManager = GridLayoutManager(itemView.context, 2)
            binding.verticalRv.adapter = childMembersAdapter

        }

        fun bind(allAnime: MainAnimeData) {
            binding.textTitle.text = binding.textTitle.context.getString(allAnime.animeType.stringId)
            childMembersAdapter.differ.submitList(allAnime.animeList)
            binding.textTitle.setOnClickListener {
                titleClickListener?.invoke(it, allAnime.type, bindingAdapterPosition)
            }
        }
    }

    class HorizontalViewHolder(private val binding: ItemHorizontalListMainRecyclerBinding) :
        MainAnimeViewHolder(binding) {
        private val childMembersAdapter by lazy { HorizontalAnimeAdapter() }

        init {
            binding.horizontalRv.addItemDecoration(
                MarginHorizontalItemDecoration(
                    rightSize = binding.horizontalRv.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin
                    ),
                    leftSize = binding.horizontalRv.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin
                    )
                )
            )
            binding.horizontalRv.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.horizontalRv.adapter = childMembersAdapter
        }

        fun bind(trending: MainAnimeData) {
            binding.textTitle.text = binding.textTitle.context.getString(trending.animeType.stringId)
            childMembersAdapter.differ.submitList(trending.animeList)
        }
    }

}