import java.io.BufferedReader
import java.io.InputStreamReader
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



fun solution(n: Int): Array<IntArray?> {
    val resultList = mutableListOf<IntArray>()

    fun hanoi(n: Int, start: Int, middle: Int, end: Int) {
        if (n == 1) {
            resultList.add(intArrayOf(start, end))
            return
        }

        hanoi(n - 1, start, end, middle) //middle을 일단 목적지로

        resultList.add(intArrayOf(start, end))

        hanoi(n - 1, middle, start, end)
    }

    hanoi(n, 1, 2, 3)

    return resultList.toTypedArray()
}

fun solution(progresses: IntArray, speeds: IntArray): IntArray {
    val daysToComplete = IntArray(progresses.size)

    for (i in progresses.indices) {
        val needCheck = 100 - progresses[i]
        daysToComplete[i] = if ((needCheck % speeds[i] == 0)) (needCheck / speeds[i]) else (needCheck / speeds[i] + 1)
    }

    val result: MutableList<Int> = ArrayList()
    var maxDays = daysToComplete[0]
    var cnt = 1

    for (i in 1..<progresses.size) {
        if (daysToComplete[i] <= maxDays) {
            cnt++ //기존 값을 축적
        } else { //더 큰 값이므로 여태까지는 저장해두고, 새로운 카운트 시작
            result.add(cnt)
            maxDays = daysToComplete[i]
            cnt = 1
        }
    }
    result.add(cnt)

    val res = IntArray(result.size)
    for (i in result.indices) {
        res[i] = result[i]
    }

    return res
}

fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
    var firstIndex = 0
    var secondIndex = 0

    for (word in goal) {
        if (firstIndex < cards1.size && word == cards1[firstIndex]) {
            firstIndex++
        } else if (secondIndex < cards2.size && word == cards2[secondIndex]) {
            secondIndex++
        } else {
            return "No"
        }
    }

    return "Yes"
}

fun solution222(progresses: IntArray, speeds: IntArray): IntArray {
    val n = progresses.size
    val daysToComplete = IntArray(n)

    // 각 작업이 완료되기까지 걸리는 일수를 계산
    for (i in 0 until n) {
        val remainingProgress = 100 - progresses[i]
        daysToComplete[i] =
            if ((remainingProgress % speeds[i] == 0)) (remainingProgress / speeds[i]) else (remainingProgress / speeds[i] + 1)
    }

    val result: MutableList<Int> = ArrayList()
    val queue: Queue<Int> = LinkedList()

    queue.offer(daysToComplete[0])
    var cnt = 1

    for (i in 1 until n) {
        if (daysToComplete[i] <= queue.peek()) {
            cnt++ // 기존 값을 축적
        } else { // 더 큰 값이므로 여태까지는 저장해두고, 새로운 카운트 시작
            result.add(cnt)
            queue.poll() // 큐에서 제거
            queue.offer(daysToComplete[i]) // 큐에 현재 값을 추가
            cnt = 1
        }
    }
    result.add(cnt) // 마지막 카운트 추가

    val resultArray = IntArray(result.size)
    for (i in result.indices) {
        resultArray[i] = result[i]
    }

    return resultArray
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()  // 지도의 크기
    val map = Array(n) { IntArray(n) }
    val visited = Array(n) { BooleanArray(n) }

    // 지도 데이터 입력
    for (i in 0..<n) {
        map[i] = br.readLine().trim().map { it - '0' }.toIntArray()
    }

    val complexes = mutableListOf<Int>()  // 각 단지별 집의 수 저장
    val directions = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))

    fun bfs(startX: Int, startY: Int): Int {
        var count = 0
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.add(Pair(startX, startY))
        visited[startX][startY] = true

        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()
            count++

            for (dir in directions) {
                val nx = x + dir[0]
                val ny = y + dir[1]
                if (nx in 0..<n && ny in 0..<n && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.add(Pair(nx, ny))
                }
            }
        }
        return count
    }

    // 모든 칸을 검사하여 아직 방문하지 않은 집이 있다면 BFS 실행
    for (i in 0..<n) {
        for (j in 0..<n) {
            if (map[i][j] == 1 && !visited[i][j]) {
                complexes.add(bfs(i, j))
            }
        }
    }

    println(complexes.size)
    complexes.sort()
    complexes.forEach { println(it) }
}

fun solutionFarAway(n: Int): Int {
    if (n == 1) return 1
    if (n == 2) return 2

    var a = 1
    var b = 2
    var sum = 0

    // 3부터 n까지 각 수에 대해 피보나치 수열 계산
    for (i in 3..n) {
        sum = (a + b) % 1234567
        a = b
        b = sum
    }

    return sum
}