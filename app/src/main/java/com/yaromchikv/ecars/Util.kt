package com.yaromchikv.ecars

fun Double.displayAcceleration() = "0-100 km/h: ${this}s"

fun Int.displayRange() = "Range: $this"

fun Int.displayPrice(): String {
    val builder = StringBuilder("Price: $")
    if (this >= 1000000)
        builder.append("%d %03d ".format(this / 1000000, (this % 1000000) / 1000))
    else if (this >= 1000)
        builder.append("%d ".format(this / 1000))
    if (this >= 100)
        builder.append("%03d".format(this % 1000))
    else
        builder.append("%d".format(this % 1000))
    return builder.toString()
}

fun getRandomImage(): Int {
    return listOf(
        R.drawable.car1,
        R.drawable.car2,
        R.drawable.car3,
        R.drawable.car4,
        R.drawable.car5,
        R.drawable.car6,
    ).random()
}