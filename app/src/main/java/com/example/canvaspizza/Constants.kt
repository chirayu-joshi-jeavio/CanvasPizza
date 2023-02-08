package com.example.canvaspizza

enum class PizzaSize(val radiusFraction: Float) {
    SMALL(0.6f),
    MEDIUM(0.8f),
    LARGE(1f)
}

enum class CheeseQuantity(val particleCount: Int) {
    NONE(0),
    REGULAR(2000),
    DOUBLE_CHEESE(4000),
    CHEESE_BUSTER(6000)
}

enum class VegQuantity(val particleCount: Int) {
    NONE(0),
    REGULAR(30),
    EXTRA(60)
}

enum class OliveQuantity(val particleCount: Int) {
    NONE(0),
    REGULAR(10),
    EXTRA(20)
}
