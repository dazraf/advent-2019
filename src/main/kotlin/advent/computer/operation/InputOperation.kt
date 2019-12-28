package advent.computer.operation

import advent.computer.CPU

object InputOperation : Operation {
  override val opCode: OpCode = 3
  override fun execute(cpu: CPU): Int {
    val input = cpu.read()
    val location = cpu.rawParameter(1)
    cpu.store(location, input)
    cpu.trace("IN $location $input")
    return cpu.ip + 2
  }

  override fun toString() = "IN"
}