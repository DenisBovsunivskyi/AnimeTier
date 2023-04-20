package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentHomeListBinding
import com.denisbovsunivskyi.animetier.domain.common.ResponseResult
import com.denisbovsunivskyi.animetier.presentation.ui.adapter.TrendingAnimeAdapter
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home.TrendingAnimeViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.MarginItemDecoration
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeList : BaseBindingFragment<FragmentHomeListBinding>() {
    private val trendingAnimeViewModel by activityViewModels<TrendingAnimeViewModel>()
    private val adapter: TrendingAnimeAdapter by lazy {
        return@lazy TrendingAnimeAdapter()
    }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeListBinding
        get() = FragmentHomeListBinding::inflate

    override fun init() {

    }

    override fun initViews() {
        super.initViews()
        binding.trendingRecycler.adapter = adapter
        binding.trendingRecycler.addItemDecoration(MarginItemDecoration(15))

    }

    override fun initListeners() {
        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(HomeListDirections.actionGlobalAuth())
        }
    }

    override fun initViewModels() {
        trendingAnimeViewModel.trendingAnimeList.observe(this) {
            when (it) {
                is ResponseResult.Success -> {
                    adapter.differ.submitList(it.data?.data)
                }

                is ResponseResult.Error -> {
                    println(it.message?.asString(requireContext()))
                }

                is ResponseResult.Loading -> {
                    println("Loading")
                }
            }
        }
    }

}