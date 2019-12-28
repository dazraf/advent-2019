package advent.computer.operation

import advent.computer.CPU

typealias OpCode = Int

interface Operation {
  val opCode: OpCode

  /**
   * @return the new IP
   */
  fun execute(cpu: CPU): Int
}

