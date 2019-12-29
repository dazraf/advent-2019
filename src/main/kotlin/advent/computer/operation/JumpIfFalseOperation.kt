package advent.computer.operation

import advent.computer.Microcode

/**
 * Checks the value of parameter 1 for 0 and jumps to the address in parameter 2 if true
 */
object JumpIfFalseOperation : Operation() {

  override val symbol = "JMF"
  override val opCode: OpCode = 6
  override fun execute(microcode: Microcode): Int {
    return when (microcode.parameter(1)) {
      0 -> microcode.parameter(2)
      else -> microcode.ip + 3
    }
  }
}