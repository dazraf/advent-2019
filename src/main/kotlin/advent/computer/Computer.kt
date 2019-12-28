package advent.computer

import advent.computer.operation.*

class Computer(
  program: IntArray,
  operations: List<Operation> = listOf(
    AddOperation,
    MultiplyOperation,
    InputOperation,
    OutputOperation,
    LessThanOperation,
    EqualsOperation,
    JumpIfFalseOperation,
    JumpIfTrueOperation
  ),
  inputSequence: Sequence<Int> = emptySequence(),
  val output: (Int) -> Unit = { println("output: $it") },
  val trace: (String) -> Unit = { }
) {

  internal val memory = Memory(program)

  private val ops = operations.map { it.opCode to it }.toMap()
  internal val input = inputSequence.iterator()

  fun runProgram() {
    generateSequence(0) {
      when (opCode(it)) {
        99 -> null.also { trace("HALT") }
        else -> processOpCode(it)
      }
    }.last()
  }

  private fun processOpCode(ip: Int): Int {
    val opCode = opCode(ip)
    val op = ops[opCode] ?: error("unknown opcode $opCode at position $ip")
    trace("EXEC @$ip $op")
    return op.execute(CPU(ip, this, trace))
  }

  private fun opCode(ip: Int): OpCode = memory[ip] % 100

  companion object {
    fun runProgram(memory: IntArray) {
      Computer(memory).runProgram()
    }
  }
}