package com.begreen.grew.src.main

import com.begreen.grew.src.main.model.NewsReponse
import com.begreen.grew.src.main.model.PostNewsRequest
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {

    @POST("/news")
    fun postRecommendedNews(@Body params: PostNewsRequest): Call<NewsReponse>


//    //피드 목록 조회 (팁끌 들러보기)
//    @GET("/posts")
//    fun getLookAroundFeed(@Query("categoryName") categoryName:String, @Query("order") order:String, @Query("search") search:String?=null,
//                          @Query("page") page:Int, @Query("limit") limit:Int)
//            : Call<LookAroundResponse>
}