package com.begreen.grew.src.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.begreen.grew.R
import com.begreen.grew.config.ApplicationClass
import com.begreen.grew.config.BaseFragment
import com.begreen.grew.databinding.FragmentHomeBinding
import com.begreen.grew.src.main.HomeFragmentView
import com.begreen.grew.src.main.HomeService
import com.begreen.grew.src.main.RecommendAdapter
import com.begreen.grew.src.main.model.NewsReponse
import com.begreen.grew.src.main.model.PostNewsRequest

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
), HomeFragmentView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = ApplicationClass.sSharedPreferences.getString("name", null)
        //Log.d("hello", intent.getStringArrayList("category")?.size.toString())

        showLoadingDialog(requireContext())
        val postNewsRequest = PostNewsRequest(arguments?.getStringArrayList("category")?.toList() as List<String>)
        HomeService(this).tryPostNews(postNewsRequest = postNewsRequest)
    }

    override fun onGetNewsSuccess(response: NewsReponse) {
        dismissLoadingDialog()
        binding.rvRecommend.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommend.adapter = RecommendAdapter(requireContext(), response.result.first)
    }

    override fun onGetNewsFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}