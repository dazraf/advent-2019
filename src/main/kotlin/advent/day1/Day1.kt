package advent.day1

import kotlin.math.floor

fun main() {
    inputSequence()
        .map { it.toInt() }
        .map(::calculateFuel)
        .sum()
        .also(::println)
}

private fun inputSequence() = generateSequence {
    readLine()?.let { if (it.isBlank()) null else it }
}

private fun calculateFuel(mass: Int): Int {
    val fuel = floor(mass.toDouble() / 3.0).toInt() - 2
    return when {
        fuel > 0 -> fuel + calculateFuel(fuel)
        else -> 0
    }
}
