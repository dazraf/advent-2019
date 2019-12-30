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
  instructionSet: List<Operation> = DEFAULT_INSTRUCTION_SET,
  val input: Iterator<Int> = emptySequence<Int>().iterator(),
  val output: (Int) -> Unit = { println("output: $it") },
  val haltHandler: () -> Unit = {},
  val trace: (String) -> Unit = {}
) {

  internal val memory = Memory(program)
  private val ops = instructionSet.map { it.opCode to it }.toMap()

  fun runProgram() {
    // Create a sequence of instruction pointers (ip), starting at zero
    generateSequence(0) { ip ->
      when (opCode(ip)) {
        // if the opcode at ip is 99 then we halt,
        // returning null to the sequence, thereby terminating it
        99 -> null.also {
          trace("HALT")
          haltHandler()
        }
        // otherwise, we process the opcode
        else -> processOpCode(ip)
      }
    }.last()
  }

  /**
   * process the opcode at instruction pointer [ip]
   */
  private fun processOpCode(ip: Int): Int {
    val opCode = opCode(ip)
    val op = ops[opCode] ?: error("unknown opcode $opCode at position $ip")
    trace("EXEC @$ip $op")
    return op.execute(Microcode(ip, this, trace))
  }

  /**
   * retrieve the [OpCode] at instruction pointer [ip]
   * N.B. the top two digits of the instruction is the opcode
   */
  private fun opCode(ip: Int): OpCode = memory[ip] % 100

  companion object {
    fun runProgram(memory: IntArray) {
      Computer(memory).runProgram()
    }

    val DEFAULT_INSTRUCTION_SET = listOf(
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