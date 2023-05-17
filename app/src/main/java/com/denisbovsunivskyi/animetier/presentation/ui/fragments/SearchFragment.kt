package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentSearchBinding
import com.denisbovsunivskyi.animetier.presentation.ui.adapter.SearchAnimeAdapter
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.search.SearchAnimeViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.MarginVerticalItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseBindingFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    private val searchAnimeViewModel by activityViewModels<SearchAnimeViewModel>()
    private val searchAnimeAdapter by lazy { SearchAnimeAdapter() }

    override fun init() {

    }

    override fun initViews() {
        binding.searchRv.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                2
            )
        }
        binding.searchRv.addItemDecoration(
            MarginVerticalItemDecoration(
                bottomSize = binding.searchRv.context.resources.getDimensionPixelSize(
                    R.dimen.recycler_margin_bottom
                ),
                leftSize = binding.searchRv.context.resources.getDimensionPixelSize(
                    R.dimen.recycler_margin
                ),
                rightSize = binding.searchRv.context.resources.getDimensionPixelSize(
                    R.dimen.recycler_margin
                )

            )
        )
        binding.searchRv.adapter = searchAnimeAdapter
    }

    override fun onDestroyView() {
        requireView().findViewById<RecyclerView>(R.id.search_rv).adapter = null
        super.onDestroyView()
    }

    override fun initListeners() {

    }

    override fun initViewModels() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                searchAnimeViewModel.getAnimeList().collect {
                    searchAnimeAdapter.submitData(lifecycle, it)
                }
            }
        }

    }


}