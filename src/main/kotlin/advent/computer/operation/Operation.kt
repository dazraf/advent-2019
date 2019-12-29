package advent.computer.operation

import advent.computer.Microcode

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
   * Executes the op code given a [microcode] interface
   * @return the new IP
   */
  abstract fun execute(microcode: Microcode): Int

  override fun toString() = symbol
}

