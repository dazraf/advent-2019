package advent.computer.operation

import advent.computer.CPU

typealias OpCode = Int

interface Operation {
  /**
   * numeric op code for this operation
   */
  val opCode: OpCode

  /**
   * @return the new IP
   */
  fun execute(cpu: CPU): Int
}

