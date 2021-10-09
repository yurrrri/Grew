package com.begreen.grew.src.main

import com.begreen.grew.src.main.model.MyPageResponse
import com.begreen.grew.src.main.model.NewsReponse
import com.begreen.grew.src.main.model.PostNewsRequest
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {

    @POST("/news")
    fun postRecommendedNews(@Body params: PostNewsRequest): Call<NewsReponse>


    @GET("/users")
    fun getLookAroundFeed(@Query("name") name:String) : Call<MyPageResponse>
}