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
import org.json.JSONException

import org.json.JSONArray

import android.preference.PreferenceManager

import android.content.SharedPreferences
import com.begreen.grew.src.main.AgeNewsAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
), HomeFragmentView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sf = ApplicationClass.sSharedPreferences

        super.onViewCreated(view, savedInstanceState)

        val json = sf.getString("category", null)
        val categories = ArrayList<String>()

        if (json != null){
            try {
                val a = JSONArray(json)
                for (i in 0 until a.length()) {
                    val category = a.optString(i)
                    categories.add(category)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        showLoadingDialog(requireContext())
        val postNewsRequest = PostNewsRequest(categories)
        HomeService(this).tryPostNews(postNewsRequest = postNewsRequest)
    }

    override fun onGetNewsSuccess(response: NewsReponse) {
        dismissLoadingDialog()
        binding.rvRecommend.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommend.adapter = RecommendAdapter(requireContext(), response.result.first)

        binding.rvManyNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvManyNews.adapter = AgeNewsAdapter(requireContext(), response.result.second)
    }

    override fun onGetNewsFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}