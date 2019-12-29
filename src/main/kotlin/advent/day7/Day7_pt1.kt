package advent.day7

import advent.computer.Computer

fun main() {
  permutation((0..4).toList())
    .map { phaseVector -> runAmplifiers(phaseVector, PROGRAM) }
    .max()
    .also { println("maximum thrust: $it") }
}

private fun runAmplifiers(phaseVector: List<Int>, program: IntArray): Int {
  return phaseVector.fold(0) { input, phase ->
    val nextInput = runProgram(program, listOf(phase, input))
    nextInput
  }
}

private fun runProgram(program: IntArray, input: List<Int>): Int {
  var output = 0
  Computer(
    program = program,
    inputSequence = input.asSequence(),
    output = { output = it }
  ).runProgram()
  return output
}

