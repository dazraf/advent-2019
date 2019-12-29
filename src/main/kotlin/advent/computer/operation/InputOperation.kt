package advent.computer.operation

import advent.computer.Microcode

/**
 * Reads from the I/O "bus" and stores the address given by parameter 1
 */
object InputOperation : Operation() {

  override val symbol = "IN"
  override val opCode: OpCode = 3
  override fun execute(microcode: Microcode): Int {
    val input = microcode.read()
    val location = microcode.rawParameter(1)
    microcode.store(location, input)
    microcode.trace("$symbol $location $input")
    return microcode.ip + 2
  }
}