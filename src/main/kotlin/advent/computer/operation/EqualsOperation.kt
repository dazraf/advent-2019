package advent.computer.operation

import advent.computer.Microcode

/**
 * Checks for equality between the values of parameters 1 and 2, and writes the result
 * to the address referenced by parameter 3
 */
object EqualsOperation : Operation() {

  override val symbol = "EQ"
  override val opCode: OpCode = 8
  override fun execute(microcode: Microcode): Int {
    val p1 = microcode.parameter(1)
    val p2 = microcode.parameter(2)
    val p3 = microcode.rawParameter(3)
    when (p1) {
      p2 -> microcode.store(p3, 1)
      else -> microcode.store(p3, 0)
    }
    return microcode.ip + 4
  }
}