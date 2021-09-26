package com.begreen.grew.src.main

import android.os.Bundle
import android.view.View
import com.begreen.grew.R
import com.begreen.grew.config.ApplicationClass
import com.begreen.grew.config.BaseFragment
import com.begreen.grew.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = ApplicationClass.sSharedPreferences.getString("name", null)
    }
}