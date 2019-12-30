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
  val memory: Memory,
  instructionSet: List<Operation> = DEFAULT_INSTRUCTION_SET,
  val input: Iterator<Int> = emptySequence<Int>().iterator(),
  val output: (Int) -> Unit = { println("output: $it") },
  val haltHandler: () -> Unit = {},
  val trace: (String) -> Unit = {}
) {

  constructor(
    program: IntArray,
    instructionSet: List<Operation> = DEFAULT_INSTRUCTION_SET,
    input: Iterator<Int> = emptySequence<Int>().iterator(),
    output: (Int) -> Unit = { println("output: $it") },
    haltHandler: () -> Unit = {},
    trace: (String) -> Unit = {}
  ) : this(Memory(program), instructionSet, input, output, haltHandler, trace)

  private val ops = instructionSet.map { it.opCode to it }.toMap()

  fun runProgram(): Computer {
    // Create a sequence of instruction pointers (ip), starting at zero
    generateSequence(CpuState(0, this, trace)) { state ->
      when (state.opCode()) {
        // if the opcode at ip is 99 then we halt,
        // returning null to the sequence, thereby terminating it
        99 -> null.also {
          trace("HALT")
          haltHandler()
        }
        // otherwise, we process the opcode
        else -> processOpCode(state)
      }
    }.last()
    return this
  }

  /**
   * process the opcode at instruction pointer in [state]
   */
  private fun processOpCode(state: CpuState): CpuState {
    val opCode = state.opCode()
    val op = ops[opCode] ?: error("unknown opcode $opCode at position ${state.ip}")
    trace("EXEC @${state.ip} $op")
    return op.execute(state)
  }

  companion object {
    fun runProgram(memory: IntArray) = Computer(memory).runProgram()

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