package advent.computer.operation

import advent.computer.Microcode

/**
 * Write the value of parameter 1 to the I/O "bus"
 */
object OutputOperation : Operation() {

  override val symbol = "OUT"
  override val opCode: OpCode = 4

  override fun execute(microcode: Microcode): Int {
    val value = microcode.parameter(1)
    microcode.write(value)
    microcode.trace("$symbol $value")
    return microcode.ip + 2
  }
}