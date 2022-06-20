package com.gaziev.qr_code_scanner.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.gaziev.qr_code_scanner.MainActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<V : ViewBinding> : Fragment() {

    abstract val inflate: (LayoutInflater, ViewGroup?, Boolean) -> V
    val mainActivity by lazy { (requireActivity() as MainActivity) }

    private var _binding: V? = null
    val binding: V get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(layoutInflater, container, false)

        mainActivity.mainToolbar.setup(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun snackbar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

}