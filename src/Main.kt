import java.util.*

fun main() {
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
}