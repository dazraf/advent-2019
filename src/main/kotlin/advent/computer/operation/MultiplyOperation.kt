package advent.computer.operation

import advent.computer.CPU

object MultiplyOperation : Operation {
  override val opCode: OpCode = 2
  override fun execute(cpu: CPU): Int {
    cpu.apply {
      val p1 = parameter(1)
      val p2 = parameter(2)
      val p3 = rawParameter(3)
      trace("MLT $p1 $p2 $p3")
      set(p3, p1 * p2)
      return ip + 4
    }
  }

  override fun toString(): String {
    return "MLT"
  }
}