package advent.computer.operation

import advent.computer.Microcode

/**
 * Checks the value of parameter 1 for non zero and jumps to address in parameter 2 if true
 */
object JumpIfTrueOperation : Operation() {

  override val symbol = "JMT"
  override val opCode: OpCode = 5
  override fun execute(microcode: Microcode): Int {
    return when (microcode.parameter(1)) {
      0 -> microcode.ip + 3
      else -> microcode.parameter(2)
    }
  }
}