package advent.computer.operation

import advent.computer.CpuState

typealias OpCode = Int

/**
 * Abstract class for all operations
 */
abstract class Operation {

  /**
   * Symbol
   */
  abstract val symbol: String

  /**
   * numeric op code for this operation
   */
  abstract val opCode: OpCode

  /**
   * Executes the op code given a [cpuState] interface
   * @return the new [CpuState]
   */
  abstract fun execute(cpuState: CpuState): CpuState

  override fun toString() = symbol
}

