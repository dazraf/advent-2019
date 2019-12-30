package advent.computer.operation

import advent.computer.CpuState

/**
 * Reads from the I/O "bus" and stores the address given by parameter 1
 */
object InputOperation : Operation() {

  override val symbol = "IN"
  override val opCode: OpCode = 3
  override fun execute(cpuState: CpuState): CpuState {
    cpuState.apply {
      val input = read()
      val location = rawParameter(1)
      store(location, input)
      trace("$symbol $location $input")
      return incrementIP(2)
    }
  }
}