package advent.day4

fun main() {
  val (start, end) = readLine()?.split('-')?.map { it.toInt() }
    ?: error("failed to read numbers")
  val allCriterion = listOf(::atLeastOneIsolatedDouble, ::neverDecrements)
  println((start..end).count { matches(it.toString(), allCriterion) })
}

typealias Criterion = (String) -> Boolean
fun matches(password: String, criteria: List<Criterion>) = criteria.all { it(password) }

fun atLeastOneIsolatedDouble(password: String) =
  password.groupDuplicates().any { it.length == 2 }

fun String.groupDuplicates(): List<String> {
  return when {
    isEmpty() -> emptyList()
    else -> {
      listOf(takeWhile { it == first() }) + dropWhile { it == first() }.groupDuplicates()
    }
  }
}

fun neverDecrements(password: String) = password
  .map { it.toInt() }
  .windowed(2)
  .all { (first, second) -> first <= second }
