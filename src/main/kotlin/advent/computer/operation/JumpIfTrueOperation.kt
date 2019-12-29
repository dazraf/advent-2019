package advent.computer.operation

import advent.computer.Microcode

object JumpIfTrueOperation : Operation {
  override val opCode: OpCode = 5
  override fun execute(microcode: Microcode): Int {
    return when (microcode.parameter(1)) {
      0 -> microcode.ip + 3
      else -> microcode.parameter(2)
    }
  }

  override fun toString() = "JMT"
}