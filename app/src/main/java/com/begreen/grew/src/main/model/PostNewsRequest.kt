package com.begreen.grew.src.main.model

import com.google.gson.annotations.SerializedName

data class PostNewsRequest(
    @SerializedName("category") val category: List<String>
)