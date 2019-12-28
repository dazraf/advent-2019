package advent.computer.operation

import advent.computer.CPU

object JumpIfFalseOperation : Operation {
  override val opCode: OpCode = 6
  override fun execute(cpu: CPU): Int {
    return when (cpu.parameter(1)) {
      0 -> cpu.parameter(2)
      else -> cpu.ip + 3
    }
  }

  override fun toString(): String {
    return "JMP_FALSE"
  }
}