package com.begreen.grew.src.main.model

data class Second(
    val category: String,
    val contents: String,
    val idx: Int,
    val img_url: String,
    val is_scrap: Int,
    val provider: String,
    val published_at: String,
    val news_url:String,
    val title: String
)