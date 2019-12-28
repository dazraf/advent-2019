package advent.day6

fun main() {
  val graph = input()
  val result = graph.keys.map(orbits(graph)).sum()
  println(result)
}

fun orbits(graph: Map<String, String>): (String) -> Int {
  val cache = mutableMapOf<String, Int>()
  return {
    orbits(graph, cache, it)
  }
}

fun orbits(graph: Map<String, String>, cache: MutableMap<String, Int>, obj: String): Int {
  return cache.computeIfAbsent(obj) {
    when (val center = graph[obj]) {
      null -> 0
      else -> 1 + orbits(graph, cache, center)
    }
  }
}

fun input(): Map<String, String> {
  return generateSequence {
    readLine()?.let { if (it.isBlank()) null else it }
  }.map { line ->
    val parts = line.split(')').also {
      if (it.size != 2) error("expected two parts but instead got $line")
    }
    parts.last() to parts.first()
  }.toMap()
}