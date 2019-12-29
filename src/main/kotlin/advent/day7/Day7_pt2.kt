package advent.day7

import advent.day7.ThreadedComputer.Companion.computer
import java.util.concurrent.CountDownLatch

fun main() {
  permutation((5..9).toList()).map { phaseVector ->
    runProgram(PROGRAM, phaseVector)
  }.max().also { println("maximum thrust: $it") }
}

private fun runProgram(program: IntArray, phaseVector: List<Int>): Int {
  val countDownLatch = CountDownLatch(5)
  val computers = createComputers(program, countDownLatch::countDown)
  val (c1, c2, c3, c4, c5) = computers
  var result = 0
  c1 bind c2 bind c3 bind c4 bind c5 bind {
    result = it
    c1.inChannel.writer(it)
  }
  // write the phase vector to the computers
  phaseVector.zip(computers).forEach { (phase, computer) -> computer.write(phase) }
  // start the loop by inserting the first data value: zero
  c1.write(0)
  // wait until all machines have halted
  countDownLatch.await()
  return result
}

fun createComputers(program: IntArray, haltHandler: () -> Unit): List<ThreadedComputer> {
  return (1..5).map { computer(program, haltHandler) }
}
