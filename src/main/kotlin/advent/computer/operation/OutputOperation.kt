package advent.computer.operation

import advent.computer.CPU

object OutputOperation : Operation {
  override val opCode: OpCode = 4

  override fun execute(cpu: CPU): Int {
    val value = cpu.parameter(1)
    cpu.write(value)
    cpu.trace("OUT $value")
    return cpu.ip + 2
  }

  override fun toString(): String {
    return "OUT"
  }
}