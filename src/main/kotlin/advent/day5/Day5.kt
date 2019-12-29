@file:Suppress("ArrayInDataClass")

package advent.day5

import advent.computer.Computer

fun main() {
  programs().forEach { program ->
    Computer(program = program, inputSequence = stdInput()).runProgram()
  }
}

fun stdInput(): Sequence<Int> {
  return generateSequence {
    print("> ")
    readLine()?.toInt()
  }
}

fun trace(message: String) {
  println("TRACE: $message")
}

fun programs(): Sequence<IntArray> {
  return generateSequence { readProgram() }
}

fun readProgram(): IntArray? {
  val line = readLine() ?: return null
  if (line.isBlank()) return null
  return line.split(',').map { it.toInt() }.toIntArray()
}
