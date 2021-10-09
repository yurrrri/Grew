package com.begreen.grew.src.main.model

data class ResultMyPage(
    val user_name: String,
    val age: Int,
    val sex: String,
    val tree: Int,
    val activity: Int,
    val stage: Int,
    val scrap:List<Second>
)