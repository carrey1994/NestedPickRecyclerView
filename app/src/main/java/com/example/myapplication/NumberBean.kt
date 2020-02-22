package com.example.myapplication

data class NumberBean(
    val uniqueId: Int,
    var number: Int,
    var isCheck: Boolean = false
)

/**
 * @param uniqueId(which layer): To mark the number in which layer. Whatever, just make sure it's unique.
 *        Like a unique id to make different from the same number but belong to different layers.
 * @param number: showed by view
 * @param isCheck: To record picked numbers.
 * **/