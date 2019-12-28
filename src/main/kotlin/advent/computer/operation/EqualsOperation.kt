package advent.computer.operation

import advent.computer.CPU

object EqualsOperation : Operation {
  override val opCode: OpCode = 8
  override fun execute(cpu: CPU): Int {
    val p1 = cpu.parameter(1)
    val p2 = cpu.parameter(2)
    val p3 = cpu.rawParameter(3)
    when (p1) {
      p2 -> cpu.store(p3, 1)
      else -> cpu.store(p3, 0)
    }
    return cpu.ip + 4
  }

  override fun toString() = "EQ"
}