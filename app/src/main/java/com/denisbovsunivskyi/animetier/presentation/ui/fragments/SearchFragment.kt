package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowCompat
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
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyActionSearchListener
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.getSoftInputMode
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseBindingFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    private val searchAnimeViewModel by activityViewModels<SearchAnimeViewModel>()
    private val searchAnimeAdapter by lazy { SearchAnimeAdapter() }
    private var originalMode : Int? = null

    override fun init() {
        initClearFocusInputs()
    }

    override fun initViews() {
        originalMode = activity?.window?.getSoftInputMode()

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
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
        binding.searchEdit.setOnActionSearchListener(object: OnKeyActionSearchListener{
            override fun onActionSearch() {
                context?.showToast("Will be added soon")
            }
        })
        binding.searchLayout.setStartIconOnClickListener {
            binding.searchEdit.clearFocus()
            context?.showToast("Will be added soon")
        }
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