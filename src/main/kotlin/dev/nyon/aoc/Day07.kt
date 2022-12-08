package dev.nyon.aoc

class Day07(override val inputString: String, override val inputLines: Sequence<String>) : Day {

    sealed interface Model
    data class Directory(val name: String, var children: MutableList<Model>, val parent: Directory?) : Model
    data class File(val name: String, val size: Int) : Model

    private var currentDir = Directory("/", mutableListOf(), null)
    private val directories = mutableListOf(currentDir).also { list ->
        inputLines.forEach { line ->
            when {
                line == "$ cd /" -> currentDir = list.first()
                line == "$ cd .." -> currentDir = currentDir.parent!!
                line.startsWith("$ cd ") -> Directory(line.removePrefix("$ cd "), mutableListOf(), currentDir).also {
                    currentDir.children.add(it)
                    currentDir = it
                    list.add(it)
                }

                line[0].isDigit() -> line.split(" ").also { (size, name) ->
                    currentDir.children.add(File(name, size.toInt()))
                }

                else -> {}
            }
        }
    }

    override fun firstPart(): Any {
        return directories.filter { it.calculateSize() <= 100000 }.sumOf { it.calculateSize() }
    }

    override fun secondPart(): Any {
        return directories.filter { it.calculateSize() >= 30000000 - (70000000 - directories.first().calculateSize()) }
            .minOf { it.calculateSize() }
    }

    private fun Directory.calculateSize(): Int = children.sumOf {
        when (it) {
            is File -> it.size
            is Directory -> it.calculateSize()
        }
    }
}