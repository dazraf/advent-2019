package advent.computer.operation

import advent.computer.Microcode

object LessThanOperation : Operation {
  override val opCode: OpCode = 7
  override fun execute(microcode: Microcode): Int {
    val p1 = microcode.parameter(1)
    val p2 = microcode.parameter(2)
    val p3 = microcode.rawParameter(3)
    when {
      p1 < p2 -> microcode.store(p3, 1)
      else -> microcode.store(p3, 0)
    }
    return microcode.ip + 4
  }

  override fun toString() = "LT"
}