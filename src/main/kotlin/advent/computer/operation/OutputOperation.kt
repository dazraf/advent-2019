package advent.computer.operation

import advent.computer.Microcode

object OutputOperation : Operation {
  override val opCode: OpCode = 4

  override fun execute(microcode: Microcode): Int {
    val value = microcode.parameter(1)
    microcode.write(value)
    microcode.trace("OUT $value")
    return microcode.ip + 2
  }

  override fun toString() = "OUT"
}