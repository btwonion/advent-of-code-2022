package dev.nyon.aoc

class Day06(override val inputString: String, override val inputLines: Sequence<String>) : Day {
    private fun findMarker(size: Int) =
        inputString.toList().windowed(size).withIndex().first { it.value.distinct().size == it.value.size }.index + size

    override fun firstPart(): Any = findMarker(4)

    override fun secondPart(): Any = findMarker(14)
}