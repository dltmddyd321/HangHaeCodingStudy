import java.util.*
import kotlin.math.max

fun mainOne() {
    val sc = Scanner(System.`in`)

    val teamA = sc.nextLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val teamB = sc.nextLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    var score = 0
    for (i in teamA.indices) {
        score += teamA[i].toInt()
        if (score > 0) {
            println("Yes")
            return
        }
        score -= teamB[i].toInt()
    }
    println("No")

    val arr = intArrayOf(1, 2, 3)
    arr.average()
}

fun main() {

}

class Solution {
    fun solutionCorrect(n: Int, left: Long, right: Long) = (left..right).map{ Math.max(it / n, it % n).toInt() + 1 }.toIntArray()

    fun solutionFailed(n: Int, left: Long, right: Long): IntArray {
        val array = Array(n) { IntArray(n) }
        array.forEachIndexed { xIndex, list ->
            list.forEachIndexed { yIndex, value ->
                val (x, y) = Pair(xIndex, yIndex)
                array[x][y] = max(x, y) + 1
            }
        }

        return array.map { it.toList() }.flatten().subList(left.toInt(), right.toInt()).toIntArray()
    }

}

