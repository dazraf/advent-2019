package advent.computer.operation

import advent.computer.Microcode

object AddOperation : Operation {
  override val opCode: OpCode = 1
  override fun execute(microcode: Microcode): Int {
    microcode.apply {
      val p1 = parameter(1)
      val p2 = parameter(2)
      val p3 = rawParameter(3)
      trace("ADD $p1 $p2 $p3")
      store(p3, p1 + p2)
      return ip + 4
    }
  }

  override fun toString() = "ADD"
}