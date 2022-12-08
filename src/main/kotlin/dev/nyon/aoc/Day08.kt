package dev.nyon.aoc

class Day08(override val inputString: String, override val inputLines: Sequence<String>) : Day {

    override fun firstPart(): Any {
        var viewableTrees = 0
        val linesSize = inputLines.toList().size
        inputLines.forEachIndexed { index, string ->
            string.withIndex().forEach { char ->
                val height = char.value.digitToInt()
                if (char.index == 0 || char.index == string.length || index == 0 || index == linesSize || string.withIndex()
                        .filter { it.index < char.index }.all { it.value.digitToInt() < height } || string.withIndex()
                        .filter { it.index > char.index }
                        .all { it.value.digitToInt() < height } || inputLines.filterIndexed { lineIndex, _ -> lineIndex < index }
                        .all { it[char.index].digitToInt() < height } || inputLines.filterIndexed { lineIndex, _ -> lineIndex > index }
                        .all { it[char.index].digitToInt() < height }
                ) viewableTrees++
            }
        }

        return viewableTrees
    }

    override fun secondPart(): Any {
        val scores = arrayListOf<Int>()
        inputLines.forEachIndexed { index, string ->
            string.withIndex().forEach { char ->
                val height = char.value.digitToInt()
                var (upStopped, leftStopped, downStopped, rightStopped) = arrayListOf(false, false, false, false)

                val up = inputLines.toList().filterIndexed { indexAbove, _ -> indexAbove < index }.reversed().sumOf {
                    if (upStopped) return@sumOf 0.0.toInt()
                    if (it[char.index].digitToInt() < height) 1
                    else {
                        upStopped = true
                        1
                    }
                }

                val down = inputLines.filterIndexed { indexBelow, _ -> indexBelow > index }.sumOf {
                    if (downStopped) return@sumOf 0.0.toInt()
                    if (it[char.index].digitToInt() < height) 1
                    else {
                        downStopped = true
                        1
                    }
                }

                val left = string.filterIndexed { charIndex, _ -> charIndex < char.index }.reversed().sumOf {
                    if (leftStopped) return@sumOf 0.0.toInt()
                    if (it.digitToInt() < height) 1
                    else {
                        leftStopped = true
                        1
                    }
                }

                val right = string.filterIndexed { charIndex, _ -> charIndex > char.index }.sumOf {
                    if (rightStopped) return@sumOf 0.0.toInt()
                    if (it.digitToInt() < height) 1
                    else {
                        rightStopped = true
                        1
                    }
                }

                scores.add(up * left * down * right)
            }
        }

        return scores.max()
    }
}