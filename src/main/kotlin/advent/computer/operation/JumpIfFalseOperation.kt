package advent.computer.operation

import advent.computer.Microcode

object JumpIfFalseOperation : Operation {
  override val opCode: OpCode = 6
  override fun execute(microcode: Microcode): Int {
    return when (microcode.parameter(1)) {
      0 -> microcode.parameter(2)
      else -> microcode.ip + 3
    }
  }

  override fun toString() = "JMF"
}