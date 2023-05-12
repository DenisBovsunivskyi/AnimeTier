package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentHomeBinding
import com.denisbovsunivskyi.animetier.presentation.model.DataItemType
import com.denisbovsunivskyi.animetier.presentation.ui.adapter.home_screen.MainAdapter
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home.AllAnimeActions
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home.AllAnimeViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.extentions.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeList : BaseBindingFragment<FragmentHomeBinding>() {
    private val allAnimeViewModel by activityViewModels<AllAnimeViewModel>()

    private val allAnimeAdapter: MainAdapter by lazy {
        return@lazy MainAdapter()
    }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun init() {

    }

    override fun initViews() {
        super.initViews()
        binding.progressBar.hide()
        binding.allRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
        }
        binding.allRecycler.adapter = allAnimeAdapter
    }


    override fun initListeners() {
        allAnimeAdapter.titleClickListener = { view, item, position ->
            when (item) {
                DataItemType.TRENDING_ANIME -> {
                    context?.showToast("Trending anime was clicked")
                }

                DataItemType.ALL_ANIME_LIST -> {
                    context?.showToast("All anime was clicked")
                }
            }

        }
        binding.swipeToRefresh.setOnRefreshListener {
            allAnimeViewModel.getAllAnimeData()
            allAnimeViewModel.getTrendingAnimeData()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    override fun initViewModels() {
        allAnimeViewModel.getMainAnimeLiveData().observe(viewLifecycleOwner) { result ->
            val newList = result.map { it.copy() }
            allAnimeAdapter.submitList(newList.toList())
        }
        allAnimeViewModel.getEventLiveData().observe(viewLifecycleOwner) { state ->
            val event = state.contentIfNotHandled
            event?.let {
                when (event) {
                    is AllAnimeActions.Success -> {
                        binding.progressBar.hide()
                    }

                    is AllAnimeActions.Failed.LoadingFailed -> {
                        binding.progressBar.hide()
                        context?.showToast(event.message.asString(requireContext()))
                    }

                    is AllAnimeActions.Loading -> {
                        binding.progressBar.show()
                    }
                }
            }

        }
    }

}