package advent.computer.operation

import advent.computer.CpuState

/**
 * Checks the value of parameter 1 for 0 and jumps to the address in parameter 2 if true
 */
object JumpIfFalseOperation : Operation() {

  override val symbol = "JMF"
  override val opCode: OpCode = 6
  override fun execute(cpuState: CpuState): CpuState {
    cpuState.apply {
      return when (parameter(1)) {
        0 -> setIP(parameter(2))
        else -> incrementIP(3)
      }
    }
  }
}