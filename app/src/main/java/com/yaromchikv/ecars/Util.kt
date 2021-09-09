package com.yaromchikv.ecars

fun Double.displayAcceleration() = "0-100 km/h: ${this}s"

fun Int.displayPrice(): String {
    val builder = StringBuilder("Price: $")
    if (this >= 1000000)
        builder.append("%d %03d ".format(this / 1000000, (this % 1000000) / 1000))
    else if (this >= 1000)
        builder.append("%d ".format(this / 1000))
    builder.append("%03d".format(this % 1000))
    return builder.toString()
}