package advent.computer.operation

import advent.computer.Microcode

/**
 * Multiplies the values of parameter 1 and 2 and stores the results in the address
 * referenced by parameter 3
 */
object MultiplyOperation : Operation() {

  override val symbol = "MUL"
  override val opCode: OpCode = 2
  override fun execute(microcode: Microcode): Int {
    microcode.apply {
      val p1 = parameter(1)
      val p2 = parameter(2)
      val p3 = rawParameter(3)
      trace("$symbol $p1 $p2 $p3")
      store(p3, p1 * p2)
      return ip + 4
    }
  }
}