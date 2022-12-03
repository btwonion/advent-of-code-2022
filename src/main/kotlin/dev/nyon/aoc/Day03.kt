package dev.nyon.aoc

class Day03(override val inputString: String, override val inputLines: List<String>) : Day {
    private val alphabet = 'a'..'z'

    override fun firstPart(): Any {
        return inputLines.map { it.take(it.length / 2) to it.takeLast(it.length / 2) }.sumOf {
            val char = it.first.toCharArray().find { char ->
                it.second.contains(char)
            }!!
            if (char.isUpperCase()) alphabet.indexOf(char.lowercaseChar()) + 27
            else alphabet.indexOf(char) + 1
        }
    }

    override fun secondPart(): Any {
        return inputLines.chunked(3).map {
            it.first().toCharArray().find { char ->
                it[1].contains(char) && it[2].contains(char)
            }!!
        }.sumOf {
            if (it.isUpperCase()) alphabet.indexOf(it.lowercaseChar()) + 27
            else alphabet.indexOf(it) + 1
        }
    }
}