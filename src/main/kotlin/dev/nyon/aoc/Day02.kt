package dev.nyon.aoc

class Day02(override val inputString: String, override val inputLines: List<String>) : Day {
    private val matches = inputLines.map {
        val first = it[0]
        val second = it[2]
        Pair(first, second)
    }

    override fun firstPart(): Any {
        return matches.map { it.first.identifyItem() to it.second.identifyItem() }.sumOf {
            var points = it.second.points
            points += it.second.play(it.first).points
            points
        }
    }

    override fun secondPart(): Any {
        return matches.sumOf {
            val matchResult = MatchResult.byChar(it.second)
            var points = matchResult.points
            points += Item.byEnemyChoice(it.first.identifyItem(), matchResult).points
            points
        }
    }

    enum class Item(val points: Int) {
        Rock(1), Paper(2), Scissors(3);

        fun play(enemy: Item): MatchResult {
            return if (this == enemy) return MatchResult.Draw
            else if ((this == Rock && enemy == Scissors) || (this == Paper && enemy == Rock) || (this == Scissors && enemy == Paper)) MatchResult.Win
            else MatchResult.Lose
        }

        companion object {
            fun byEnemyChoice(enemy: Item, result: MatchResult): Item = when (result) {
                MatchResult.Win -> when (enemy) {
                    Rock -> Paper
                    Paper -> Scissors
                    Scissors -> Rock
                }

                MatchResult.Draw -> enemy

                MatchResult.Lose -> when (enemy) {
                    Rock -> Scissors
                    Paper -> Rock
                    Scissors -> Paper
                }
            }
        }
    }

    enum class MatchResult(val points: Int) {
        Lose(0), Draw(3), Win(6);

        companion object {
            fun byChar(char: Char): MatchResult = when (char) {
                'X' -> Lose
                'Y' -> Draw
                'Z' -> Win
                else -> Draw
            }
        }
    }

    private fun Char.identifyItem(): Item = when (this) {
        'A', 'X' -> Item.Rock
        'B', 'Y' -> Item.Paper
        'C', 'Z' -> Item.Scissors
        else -> Item.Rock
    }
}