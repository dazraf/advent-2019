package advent.computer.operation

import advent.computer.CPU

object JumpIfTrueOperation : Operation {
  override val opCode: OpCode = 5
  override fun execute(cpu: CPU): Int {
    return when (cpu.parameter(1)) {
      0 -> cpu.ip + 3
      else -> cpu.parameter(2)
    }
  }

  override fun toString(): String {
    return "JMP_TRUE"
  }
}