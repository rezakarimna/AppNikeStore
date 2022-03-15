package com.reza.appnikestore.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reza.appnikestore.common.NikeFragment
import com.reza.appnikestore.databinding.FragmentProfileBinding

class ProfileFragment:NikeFragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater , container , false)
        return binding.root
    }
}