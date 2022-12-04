package dev.nyon.aoc

class Day01(override val inputString: String, override val inputLines: Sequence<String>) : Day {
    private val summed = inputString.split("\n\n").map { s -> s.lines().sumOf { it.toInt() } }
    override fun firstPart(): Int = summed.max()

    override fun secondPart(): Int = summed.sorted().takeLast(3).sum()
}