package com.begreen.grew.src.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.begreen.grew.R
import com.begreen.grew.config.BaseFragment
import com.begreen.grew.databinding.FragmentActivityBinding

class ActionFragment : BaseFragment<FragmentActivityBinding>(
    FragmentActivityBinding::bind,
    R.layout.fragment_activity
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgRecommend.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.keiti.re.kr:8445/site/keiti/ex/board/View.do?cbIdx=235&bcIdx=32829"))
            startActivity(browserIntent)
        }

        binding.imgMyActivity.setOnClickListener{
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.asiae.co.kr/article/2021082205275490426"))
            startActivity(browserIntent)
        }
    }
}