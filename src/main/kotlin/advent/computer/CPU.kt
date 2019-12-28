package advent.computer

class CPU(val ip: Int, private val computer: Computer, val trace: (String) -> Unit) {
  // memory ops
  fun fetch(address: Int) = computer.memory[address]

  fun set(address: Int, value: Int) {
    computer.memory[address] = value
  }

  // parameter ops
  fun rawParameter(position: Int) =
    fetch(ip + position).also { trace("raw parameter @$position $it") }

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
  fun read() = computer.input.next()

  fun write(value: Int) = computer.output(value)
}