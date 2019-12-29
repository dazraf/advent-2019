package advent.computer.operation

import advent.computer.Microcode

object InputOperation : Operation {
  override val opCode: OpCode = 3
  override fun execute(microcode: Microcode): Int {
    val input = microcode.read()
    val location = microcode.rawParameter(1)
    microcode.store(location, input)
    microcode.trace("IN $location $input")
    return microcode.ip + 2
  }

  override fun toString() = "IN"
}