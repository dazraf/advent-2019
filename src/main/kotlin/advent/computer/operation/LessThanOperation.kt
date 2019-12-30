package advent.computer.operation

import advent.computer.CpuState

/**
 * Checks that value of parameter 1 is less than parameter 2 and stores the result
 * in the address referenced in parameter 3
 */
object LessThanOperation : Operation() {

  override val symbol = "LT"
  override val opCode: OpCode = 7
  override fun execute(cpuState: CpuState): CpuState {
    cpuState.apply {
      val p1 = parameter(1)
      val p2 = parameter(2)
      val p3 = rawParameter(3)
      when {
        p1 < p2 -> store(p3, 1)
        else -> store(p3, 0)
      }
      return incrementIP(4)
    }
  }
}