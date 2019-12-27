package advent.day4

fun main() {
  val (start, end) = readLine()?.split('-')?.map { it.toInt() } ?: error(
    "failed to read numbers"
  )
  println((start..end).count { matches(it, allCriterion) })
}

val allCriterion = listOf(::twoAdjacentAreEqual, ::neverDecrements)

typealias Criterion = (Int) -> Boolean
typealias Criteria = List<Criterion>

fun matches(password: Int, criteria: Criteria) = criteria.all { it(password) }

fun twoAdjacentAreEqual(password: Int) = password.toString()
  .map { it.toInt() }
  .rle()
  .any { it.size == 2 }

fun List<Int>.rle(): List<List<Int>> {
  return when {
    isEmpty() -> emptyList()
    else -> listOf(takeWhile { it == first() }) + dropWhile { it == first() }.rle()
  }
}

fun neverDecrements(password: Int) = password.toString()
  .map { it.toInt() }
  .windowed(2)
  .all { (first, second) -> first <= second }
