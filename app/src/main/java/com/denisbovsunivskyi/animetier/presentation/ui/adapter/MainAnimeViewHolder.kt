package com.denisbovsunivskyi.animetier.presentation.ui.adapter

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


    class AllAnimeViewHolder(private val binding: ItemVerticalListMainRecyclerBinding) :
        MainAnimeViewHolder(binding) {
        fun bind(allAnime: MainAnimeData) {
            binding.textTitle.text = binding.textTitle.context.getString(allAnime.type.stringId)
            binding.verticalRc.addItemDecoration(
                MarginVerticalItemDecoration(
                    rightSize = binding.verticalRc.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin
                    ),
                    bottomSize = binding.verticalRc.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin_bottom
                    ),
                    leftSize = binding.verticalRc.context.resources.getDimensionPixelSize(
                        R.dimen.recycler_margin)
                )
            )
            val childMembersAdapter = VerticalGridAnimeAdapter()
            childMembersAdapter.differ.submitList(allAnime.animeList)
            binding.verticalRc.layoutManager = GridLayoutManager(itemView.context, 2)
            binding.verticalRc.adapter = childMembersAdapter
            binding.textTitle.setOnClickListener {
                titleClickListener?.invoke(it, allAnime.type, bindingAdapterPosition)
            }
        }
    }

    class TrendingViewHolder(private val binding: ItemHorizontalListMainRecyclerBinding) :
        MainAnimeViewHolder(binding) {
        fun bind(trending: MainAnimeData) {
            binding.textTitle.text = binding.textTitle.context.getString(trending.type.stringId)
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
            val childMembersAdapter = TrendingAnimeAdapter()
            childMembersAdapter.differ.submitList(trending.animeList)
            binding.horizontalRv.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.horizontalRv.adapter = childMembersAdapter

        }
    }

}