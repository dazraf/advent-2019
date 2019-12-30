package advent.day7

import advent.day7.ThreadedComputer.Companion.computer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger

fun main() {
  val allowablePhaseRange = (5..9)
  permutation((allowablePhaseRange).toList()).map { phaseVector ->
    runProgram(PROGRAM, phaseVector)
  }.max().also { println("maximum thrust: $it") }
}

/**
 * Execute a program, given a vector of phase configurations for a chain of machines as
 * described by https://adventofcode.com/2019/day/7
 */
private fun runProgram(program: IntArray, phaseVector: List<Int>): Int {
  val countDownLatch = CountDownLatch(5)
  val computers = createComputers(program, countDownLatch::countDown)
  bindInputsToOutputs(computers)
  val result = bindLastComputerOutput(computers)
  setPhaseConfigurationForComputers(phaseVector, computers)
  // start the loop by inserting the first data value: zero
  computers.first().write(0)
  // wait until all machines have halted
  countDownLatch.await()
  return result.get()
}

/**
 * write the phase vector to the computers
 */
private fun setPhaseConfigurationForComputers(
  phaseVector: List<Int>,
  computers: List<ThreadedComputer>
) {
  phaseVector.zip(computers).forEach { (phase, computer) -> computer.write(phase) }
}

/**
 * bind the output of the final computer into a variable state;
 * and also feedback to beginning of the chain
 */
private fun bindLastComputerOutput(computers: List<ThreadedComputer>): AtomicInteger {
  val result = AtomicInteger()
  computers.last() bind { output ->
    result.set(output)
    computers.first().inChannel.writer(output)
  }
  return result
}

/**
 * bind the output of a computer to the input of the proceeding one
 */
private fun bindInputsToOutputs(computers: List<ThreadedComputer>) {
  computers.windowed(2).forEach { (first, second) -> first bind second }
}

fun createComputers(program: IntArray, haltHandler: () -> Unit): List<ThreadedComputer> {
  return (1..5).map { computer(program, haltHandler) }
}
