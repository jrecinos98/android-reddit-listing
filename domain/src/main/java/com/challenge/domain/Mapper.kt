package com.challenge.domain

fun String?.orEmpty() : String {
    return this ?: ""
}

fun Int?.orZero() : Int {
    return this ?: 0
}