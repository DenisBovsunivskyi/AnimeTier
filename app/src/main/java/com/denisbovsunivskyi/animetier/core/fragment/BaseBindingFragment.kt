package com.denisbovsunivskyi.animetier.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class BaseBindingFragment<VDB : ViewDataBinding> : BaseFragment() {

    private var _binding: ViewDataBinding? = null
    private var savedInstanceState: Bundle? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: VDB get() = requireNotNull(_binding) as VDB

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
