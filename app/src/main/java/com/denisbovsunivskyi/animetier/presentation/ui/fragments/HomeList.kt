package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentHomeListBinding
import com.denisbovsunivskyi.animetier.domain.utils.ResponseResult
import com.denisbovsunivskyi.animetier.presentation.ui.adapter.AllAnimeAdapter
import com.denisbovsunivskyi.animetier.presentation.ui.adapter.TrendingAnimeAdapter
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home.AllAnimeViewModel
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home.TrendingAnimeViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.MarginHorizontalItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeList : BaseBindingFragment<FragmentHomeListBinding>() {
    private val trendingAnimeViewModel by activityViewModels<TrendingAnimeViewModel>()
    private val allAnimeViewModel by activityViewModels<AllAnimeViewModel>()
    private val trendingAdapter: TrendingAnimeAdapter by lazy {
        return@lazy TrendingAnimeAdapter()
    }
    private val allAnimeAdapter: AllAnimeAdapter by lazy {
        return@lazy AllAnimeAdapter()
    }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeListBinding
        get() = FragmentHomeListBinding::inflate

    override fun init() {

    }

    override fun initViews() {
        super.initViews()
        binding.progressBar.hide()
        binding.trendingRecycler.adapter = trendingAdapter
        binding.trendingRecycler.addItemDecoration(
            MarginHorizontalItemDecoration(
                rightSize =   requireContext().resources.getDimensionPixelSize(
                    R.dimen.recycler_margin
                )
            )
        )
        binding.allRecycler.adapter = allAnimeAdapter
        binding.allRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun initListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            allAnimeViewModel.getAlLAnime()
            trendingAnimeViewModel.getTrendingAnime()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    override fun initViewModels() {
        trendingAnimeViewModel.trendingAnimeList.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseResult.Success -> {
                    println("Trending Success")
                    trendingAdapter.differ.submitList(it.data?.data)
                }

                is ResponseResult.Error -> {
                    println(it.message?.asString(requireContext()))
                }

                is ResponseResult.Loading -> {

                    println("Loading")
                }
            }
        }
        allAnimeViewModel.getAllAnimeList().observe(viewLifecycleOwner) {
            when (it) {
                is ResponseResult.Success -> {
                    binding.progressBar.hide()
                    println("All Success")
                    allAnimeAdapter.differ.submitList(it.data?.data)
                }

                is ResponseResult.Error -> {
                    binding.progressBar.hide()
                    println(it.message?.asString(requireContext()))
                }

                is ResponseResult.Loading -> {
                    binding.progressBar.show()
                }
            }
        }
    }

}