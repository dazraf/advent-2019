package advent.computer.operation

import advent.computer.CpuState

/**
 * Write the value of parameter 1 to the I/O "bus"
 */
object OutputOperation : Operation() {

  override val symbol = "OUT"
  override val opCode: OpCode = 4
  override fun execute(cpuState: CpuState): CpuState {
    cpuState.apply {
      val value = parameter(1)
      write(value)
      trace("$symbol $value")
      return incrementIP(2)
    }
  }
}