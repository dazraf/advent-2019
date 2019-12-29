package advent.computer.operation

import advent.computer.Microcode

/**
 * Add the values of parameter 1 and 2 to the address referenced by parameter 3
 */
object AddOperation : Operation() {

  override val symbol = "ADD"

  //To change initializer of created properties use File | Settings | File Templates.
  override val opCode: OpCode = 1

  override fun execute(microcode: Microcode): Int {
    microcode.apply {
      val p1 = parameter(1)
      val p2 = parameter(2)
      val p3 = rawParameter(3)
      trace("$symbol $p1 $p2 $p3")
      store(p3, p1 + p2)
      return ip + 4
    }
  }
}