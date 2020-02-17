package com.example.myapplication

data class NumberBean(
    val layer: Int,//Unique id to make different from the same number but belong to different layers
    var number: Int,
    var isCheck: Boolean = false
)