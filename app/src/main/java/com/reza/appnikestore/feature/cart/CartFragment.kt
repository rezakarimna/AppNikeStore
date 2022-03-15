package com.reza.appnikestore.feature.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reza.appnikestore.common.NikeFragment
import com.reza.appnikestore.databinding.FragmentCartBinding
import com.reza.appnikestore.databinding.FragmentProfileBinding

class CartFragment:NikeFragment() {
    lateinit var binding: FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater , container , false)
        return binding.root
    }
}