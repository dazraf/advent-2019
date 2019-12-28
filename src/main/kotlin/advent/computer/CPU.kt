package advent.computer

/**
 * Abstract for a CPU "microcode" that's used by an [advent.computer.operation.Operation]
 */
class CPU(val ip: Int, private val computer: Computer, val trace: (String) -> Unit) {

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
   * performed. This is equivalent to [Mode.IMMEDIATE]
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
      Mode.POSITION -> computer.memory[paramValue]
      Mode.IMMEDIATE -> paramValue
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
}