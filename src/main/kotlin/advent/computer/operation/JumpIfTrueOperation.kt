package advent.computer.operation

import advent.computer.CpuState

/**
 * Checks the value of parameter 1 for non zero and jumps to address in parameter 2 if true
 */
object JumpIfTrueOperation : Operation() {

  override val symbol = "JMT"
  override val opCode: OpCode = 5
  override fun execute(cpuState: CpuState): CpuState {
    cpuState.apply {
      return when (parameter(1)) {
        0 -> incrementIP(3)
        else -> setIP(parameter(2))
      }
    }
  }
}