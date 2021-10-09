package com.begreen.grew.src.main

import com.begreen.grew.src.main.model.MyPageResponse
import com.begreen.grew.src.main.model.NewsReponse

interface HomeFragmentView {
    fun onGetNewsSuccess(response: NewsReponse)
    fun onGetNewsFailure(message: String)

    fun onGetMyPageSuccess(response: MyPageResponse)
    fun onGetMyPageFailure(message: String)
}