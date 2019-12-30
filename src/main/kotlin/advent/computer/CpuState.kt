package advent.computer

import advent.computer.operation.OpCode

/**
 * Abstract for a CPU's state and "microcode" that's used by an [advent.computer.operation.Operation]
 * to update this state. Memory and IO ops have side effects. Updates to the registers of the machine
 * are immutable and side-effect free.
 */
data class CpuState(
  val ip: Int, private val computer: Computer, val trace: (String) -> Unit
) {

  // memory ops

  /**
   * fetch a value from a memory [address]
   */
  fun fetch(address: Int) = computer.memory[address]

  /**
   * store a [value] to a memory [address]
   */
  fun store(address: Int, value: Int) {
    computer.memory[address] = value
  }

  // parameter ops

  /**
   * Read the raw value of a parameter from a given [position]. No address translation is
   * performed. This is equivalent to [ParameterMode.IMMEDIATE]
   */
  fun rawParameter(position: Int) =
    fetch(ip + position).also { trace("raw parameter @$position $it") }

  /**
   * Read the value of a parameter from a given [position], performing any de-referencing
   * depending on the instruction's parameter mode
   */
  fun parameter(position: Int): Int {
    val mode = InstructionFunctions.modeFromInstruction(computer.memory[ip], position)
    val paramValue = fetch(ip + position)
    return when (mode) {
      ParameterMode.POSITION -> computer.memory[paramValue]
      ParameterMode.IMMEDIATE -> paramValue
    }.also {
      trace("parameter @$position $it")
    }
  }

  // I/O ops

  /**
   * Read from IO subsystem
   */
  fun read() = computer.input.next()

  /**
   * Write a [value] to the IO subsystem
   */
  fun write(value: Int) = computer.output(value)

  fun setIP(newIP: Int) = copy(ip = newIP)
  fun incrementIP(delta: Int) = setIP(ip + delta)

  /**
   * retrieve the [OpCode] at instruction pointer [ip]
   * N.B. the top two digits of the instruction is the opcode
   */
  fun opCode(ip: Int): OpCode = computer.memory[ip] % 100

  /**
   * retrieve the [OpCode] at the instruction pointer
   */
  fun opCode(): OpCode = opCode(ip)
}