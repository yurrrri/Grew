package com.begreen.grew.src.main

import com.begreen.grew.config.ApplicationClass
import com.begreen.grew.src.main.model.NewsReponse
import com.begreen.grew.src.main.model.PostNewsRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView) {

    fun tryPostNews(postNewsRequest: PostNewsRequest){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.postRecommendedNews(postNewsRequest).enqueue(object: Callback<NewsReponse> {
            override fun onResponse(call: Call<NewsReponse>, response: Response<NewsReponse>) {
                view.onGetNewsSuccess(response.body() as NewsReponse)
            }

            override fun onFailure(call: Call<NewsReponse>, t: Throwable) {
                view.onGetNewsFailure(t.message ?: "통신 오류")
            }
        })
    }

}