package advent.day6

fun main() {
  val graph = input()
  val totalOrbits = graph.keys.map(orbits(graph)).sum()
  println("total orbits: $totalOrbits")
  val you = path(graph, "YOU").asReversed()
  val santa = path(graph, "SAN").asReversed()
  val commonParent = you.zip(santa).last { it.first == it.second }.first
  val yd = distance(graph, "YOU", commonParent) - 1
  val sd = distance(graph, "SAN", commonParent) - 1
  println("minimum orbit transfers: ${yd + sd}")
}

fun orbits(graph: Map<String, String>): (String) -> Int {
  val cache = mutableMapOf<String, Int>()
  return {
    orbits(graph, cache, it)
  }
}

fun distance(graph: Map<String, String>, from: String, to: String): Int {
  val next = graph[from]
  return when {
    from == to -> 0
    next == null -> 0
    else -> 1 + distance(graph, next, to)
  }
}

fun path(graph: Map<String, String>, obj: String): List<String> {
  return sequence {
    yield(obj)
    when (val next = graph[obj]) {
      null -> {
      }
      else -> yieldAll(path(graph, next))
    }
  }.toList()
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
  println("paste in the data followed by a blank line")
  return generateSequence {
    readLine()?.let { if (it.isBlank()) null else it }
  }.map { line ->
    val parts = line.split(')').also {
      if (it.size != 2) error("expected two parts but instead got $line")
    }
    parts.last() to parts.first()
  }.toMap()
}