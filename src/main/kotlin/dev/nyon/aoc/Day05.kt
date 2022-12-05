package dev.nyon.aoc

class Day05(override val inputString: String, override val inputLines: Sequence<String>) : Day {
    private val parts = inputString.split("\n\n").map { it.lines() as MutableList<String> }
    private val idIndexes =
        parts.first().last().withIndex().filter { !it.value.isWhitespace() }.map { it.index to it.value.digitToInt() }
            .also { parts.first().removeLast() }
    private val stacks = buildMap {
        idIndexes.forEach { pair ->
            parts.first().forEach { line ->
                if (this[pair.second] == null) put(pair.second, ArrayDeque())
                val char: Char? = line.getOrNull(pair.first)
                if ((char != null) && !char.isWhitespace()) this[pair.second]!! += char
            }
        }
    }

    override fun firstPart(): Any {
        getCommands().forEach { (amount, from, to) ->
            repeat(amount) {
                stacks[to]!!.addFirst(stacks[from]!!.removeFirst())
            }
        }

        return buildString {
            stacks.forEach {
                append(it.value.first())
            }
        }
    }

    override fun secondPart(): Any {
        getCommands().forEach { (amount, from, to) ->
            (1..amount).map { stacks[from]!!.removeFirst() }.asReversed().forEach {
                stacks[to]!!.addFirst(it)
            }
        }

        return buildString {
            stacks.forEach {
                append(it.value.first())
            }
        }
    }

    private fun getCommands(): List<List<Int>> = parts[1].map { s ->
        s.removePrefix("move ").split(" from ", " to ").map { it.toInt() }
    }
}