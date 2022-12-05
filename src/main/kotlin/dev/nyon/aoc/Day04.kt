package dev.nyon.aoc

class Day04(override val inputString: String, override val inputLines: Sequence<String>) : Day {
    private val pairs = inputLines.map {
        val split = it.split(",").map { part ->
            val ints = part.split("-")
            ints[0].toInt()..ints[1].toInt()
        }
        split[0] to split[1]
    }

    override fun firstPart(): Any {
        return pairs.sumOf {
            if (it.first.all { int -> it.second.contains(int) } || it.second.all { int -> it.first.contains(int) }) 1.0.toInt() else 0.0.toInt()
        }
    }

    override fun secondPart(): Any {
        return pairs.sumOf {
            if (it.first.any { int -> it.second.contains(int) } || it.second.any { int -> it.first.contains(int) }) 1.0.toInt() else 0.0.toInt()
        }
    }
}