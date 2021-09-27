package com.begreen.grew.src.main.model

import com.begreen.grew.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class NewsReponse(@SerializedName("result") val result: ResultNews): BaseResponse()