package dev.nyon.aoc

import kotlin.io.path.Path
import kotlin.io.path.readText

const val currentDay = 4

@Suppress("SENSELESS_COMPARISON")
fun main() {
    val part = 2

    val dayText = Path("src/main/kotlin/dev/nyon/aoc/inputs", "Day$currentDay.txt").readText()
    getDayObject(dayText, dayText.lines().asSequence()).also {
        println(
            if (part == 1) it.firstPart()
            else it.secondPart()
        )
    }
}

interface Day{
    val inputString: String
    val inputLines: Sequence<String>

    fun firstPart(): Any
    fun secondPart(): Any
}

fun getDayObject(inputString: String, inputLines: Sequence<String>): Day = when (currentDay) {
    1 -> Day01(inputString, inputLines)
    2 -> Day02(inputString, inputLines)
    3 -> Day03(inputString, inputLines)
    4 -> Day04(inputString, inputLines)
    5 -> Day05(inputString, inputLines)
    else -> Day01(inputString, inputLines)
}