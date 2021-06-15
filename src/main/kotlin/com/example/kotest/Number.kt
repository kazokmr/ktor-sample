package com.example.kotest

data class Number(val value: Int) {
    fun isOdd() = value % 2 != 0
    fun isRange(min: Int, max: Int) = value in min..max
}

