package advent.computer

import advent.computer.operation.*

/**
 * This is the "computer" that's used by several of the challenges
 * It takes program and executes it using the [runProgram] method.
 * Optionally, it can be configured with
 * - an instruction set;
 * - a sequence of Ints from an I/O bus;
 * - a function to output Ints to an I/O bus;
 * - a function to receive "trace" level logs from the internals of the computer
 */
class Computer(
  program: IntArray,
  instructionSet: List<Operation> = DEFAULT_OPERATORS,
  inputSequence: Sequence<Int> = emptySequence(),
  val output: (Int) -> Unit = { println("output: $it") },
  val haltHandler: () -> Unit = {},
  val trace: (String) -> Unit = {}
) {

  internal val memory = Memory(program)
  private val ops = instructionSet.map { it.opCode to it }.toMap()
  internal val input = inputSequence.iterator()

  fun runProgram() {
    generateSequence(0) {
      when (opCode(it)) {
        99 -> null.also {
          trace("HALT")
          haltHandler()
        }
        else -> processOpCode(it)
      }
    }.last()
  }

  private fun processOpCode(ip: Int): Int {
    val opCode = opCode(ip)
    val op = ops[opCode] ?: error("unknown opcode $opCode at position $ip")
    trace("EXEC @$ip $op")
    return op.execute(Microcode(ip, this, trace))
  }

  private fun opCode(ip: Int): OpCode = memory[ip] % 100

  companion object {
    fun runProgram(memory: IntArray) {
      Computer(memory).runProgram()
    }

    val DEFAULT_OPERATORS = listOf(
      AddOperation,
      MultiplyOperation,
      InputOperation,
      OutputOperation,
      LessThanOperation,
      EqualsOperation,
      JumpIfFalseOperation,
      JumpIfTrueOperation
    )
  }
}