package advent.day3

import advent.day3.Vector.Companion.ZERO

fun main() {
  val paths = generateSequence(Path()) { read()?.parsePath(it.id + 1) }.toList()
  val game = paths.fold(Game()) { game, path -> game.applyPath(path) }
  println(game.nearestCross)
}

private data class Vector(val x: Long, val y: Long) {
  companion object {
    val ZERO = Vector(0, 0)
  }

  override fun toString(): String {
    return "($x,$y)"
  }
}

private enum class Direction(val vector: Vector) {
  U(Vector(0, 1)),
  D(Vector(0, -1)),
  L(Vector(-1, 0)),
  R(Vector(1, 0))
}

private data class Step(val direction: Direction, val magnitude: Long)
private data class Path(val id: Int = 0, val steps: List<Step> = emptyList())
private data class GridPointData(
  val coordinates: Vector, val pathIds: Set<Int>, val totalDistance: Long
)

private data class Game(val grid: Map<Vector, GridPointData> = emptyMap())

private operator fun Vector.plus(rhs: Vector) = Vector(x + rhs.x, y + rhs.y)
private operator fun Vector.times(rhs: Long) = Vector(x * rhs, y * rhs)

private fun Vector.walk(step: Step): List<Vector> =
  (1..step.magnitude).map { this + step.direction.vector * it }

private fun Vector.walk(steps: List<Step>): List<Vector> =
  when {
    steps.isEmpty() -> emptyList()
    else -> walk(steps.first()).let { it + it.last().walk(steps.drop(1)) }
  }

private fun Path.walk() = ZERO.walk(steps)

private val Game.nearestCross
  get() = crossings.minBy { it.totalDistance } ?: error("no crossings")

private val Game.crossings
  get() = grid.filter { (_, gridPoint) -> gridPoint.pathIds.size > 1 }.map { (_, gridPoint) -> gridPoint }

private fun Game.applyPath(path: Path) =
  Game(
    grid + path.walk().mapIndexed { index, point ->
      point to applyToGridPointData(point, path.id, 1L + index)
    })

private fun Game.applyToGridPointData(point: Vector, pathId: Int, distance: Long) =
  when (val gridPointData = grid[point]) {
    null -> GridPointData(
      coordinates = point, pathIds = setOf(pathId), totalDistance = distance
    )
    else -> gridPointData.copy(
      pathIds = gridPointData.pathIds + pathId,
      totalDistance = gridPointData.totalDistance + distance
    )
  }

private fun read() = readLine()?.let {
  when {
    it.isBlank() -> null
    else -> it
  }
}

private fun String.parsePath(id: Int) = Path(id, this.split(',').map(::parseStep))

private val re = Regex("([LRUD])(\\d+)")

private fun parseStep(txt: String) = re.matchEntire(txt)?.destructured?.let { (d, m) ->
  Step(Direction.valueOf(d), m.toLong())
} ?: error("failed to match $txt")

