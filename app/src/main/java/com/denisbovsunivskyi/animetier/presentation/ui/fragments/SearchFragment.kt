package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentSearchBinding
import com.denisbovsunivskyi.animetier.presentation.ui.adapter.SearchAnimeAdapter
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.search.SearchAnimeViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.MarginVerticalItemDecoration
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyActionSearchListener
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.getSoftInputMode
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseBindingFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    private val searchAnimeViewModel by activityViewModels<SearchAnimeViewModel>()
    private val searchAnimeAdapter by lazy { SearchAnimeAdapter() }
    private var originalMode: Int? = null

    override fun init() {
        initClearFocusInputs()
    }

    override fun initViews() {
        originalMode = activity?.window?.getSoftInputMode()

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
        super.onDestroyView()
        requireView().findViewById<RecyclerView>(R.id.search_rv).adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        originalMode?.let { activity?.window?.setSoftInputMode(it) }
    }

    private fun initClearFocusInputs() {
        binding.searchEdit.setTargetForCleanFocus(binding.searchLayout)
    }

    override fun initListeners() {
        var searchText: String? = null
        binding.searchEdit.setOnActionSearchListener(object : OnKeyActionSearchListener {
            override fun onActionSearch() {
                searchText = binding.searchEdit.text.toString()
                if (searchText?.isEmpty() == true) {
                    searchText = null
                }
                searchAnimeViewModel.getAnimeList(searchText)
            }
        })
        binding.searchLayout.setStartIconOnClickListener {
            searchText = binding.searchEdit.text.toString()
            if (searchText?.isEmpty() == true) {
                searchText = null
            }
            binding.searchEdit.clearFocus()
            searchAnimeViewModel.getAnimeList(searchText)
        }
    }

    override fun initViewModels() {
        searchAnimeViewModel.getSearchAnimeList().observe(viewLifecycleOwner) {
            searchAnimeAdapter.submitData(lifecycle, it)
        }
    }

}