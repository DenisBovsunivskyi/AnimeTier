package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentHomeBinding
import com.denisbovsunivskyi.animetier.presentation.ui.adapter.home_screen.MainAdapter
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home.AllAnimeActions
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.home.AllAnimeViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {
    private val allAnimeViewModel by activityViewModels<AllAnimeViewModel>()

    private  val allAnimeAdapter: MainAdapter by lazy { MainAdapter() }
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
        binding.swipeToRefresh.setOnRefreshListener {
            allAnimeViewModel.fetchAllData()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        requireView().findViewById<RecyclerView>(R.id.all_recycler).adapter = null
        super.onDestroyView()
    }

    override fun initViewModels() {
        allAnimeViewModel.getMainAnimeLiveData().observe(
            this.viewLifecycleOwner
        ) { result ->
            val newList = result.map { it.copy() }
            allAnimeAdapter.submitList(newList)
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