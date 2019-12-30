package advent.computer.operation

import advent.computer.CpuState

/**
 * Checks for equality between the values of parameters 1 and 2, and writes the result
 * to the address referenced by parameter 3
 */
object EqualsOperation : Operation() {

  override val symbol = "EQ"
  override val opCode: OpCode = 8
  override fun execute(cpuState: CpuState): CpuState {
    cpuState.apply {
      val p1 = parameter(1)
      val p2 = parameter(2)
      val p3 = rawParameter(3)
      when (p1) {
        p2 -> cpuState.store(p3, 1)
        else -> cpuState.store(p3, 0)
      }
      return incrementIP(4)
    }
  }
}