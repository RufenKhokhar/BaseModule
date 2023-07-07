package com.rkgroup.base.basemodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(private val viewCallbacks: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
    Fragment() {

    private lateinit var _binding: VB
    protected val binding: VB get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = viewCallbacks.invoke(layoutInflater, container, false)
        return _binding.root
    }
}