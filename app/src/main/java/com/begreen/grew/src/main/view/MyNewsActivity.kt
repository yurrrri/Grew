package com.begreen.grew.src.main.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.begreen.grew.config.BaseActivity
import com.begreen.grew.databinding.ActivityMyNewsBinding
import com.begreen.grew.src.main.AgeNewsAdapter
import com.begreen.grew.src.main.HomeFragmentView
import com.begreen.grew.src.main.HomeService
import com.begreen.grew.src.main.RecommendAdapter
import com.begreen.grew.src.main.model.MyPageResponse
import com.begreen.grew.src.main.model.NewsReponse

class MyNewsActivity : BaseActivity<ActivityMyNewsBinding>(ActivityMyNewsBinding::inflate), HomeFragmentView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog(this)
        HomeService(this).tryGetMyPage("이유리")
    }

    override fun onGetNewsSuccess(response: NewsReponse) {
        TODO("Not yet implemented")
    }

    override fun onGetNewsFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetMyPageSuccess(response: MyPageResponse) {
        dismissLoadingDialog()
        binding.rvManyNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvManyNews.adapter = AgeNewsAdapter(this, response.result.scrap)
    }

    override fun onGetMyPageFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}