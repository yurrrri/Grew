package com.begreen.grew.src.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.begreen.grew.R
import com.begreen.grew.config.BaseFragment
import com.begreen.grew.databinding.FragmentActivityBinding

class ActionFragment : BaseFragment<FragmentActivityBinding>(
    FragmentActivityBinding::bind,
    R.layout.fragment_activity
) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }
}