package advent.computer.operation

import advent.computer.Microcode

typealias OpCode = Int

interface Operation {
  /**
   * numeric op code for this operation
   */
  val opCode: OpCode

  /**
   * @return the new IP
   */
  fun execute(microcode: Microcode): Int
}

