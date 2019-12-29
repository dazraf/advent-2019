package advent.computer

import advent.computer.operation.OpCode

object InstructionFunctions {
  fun opCodeFromInstruction(instruction: Int): OpCode = instruction % 100

  fun modeFromInstruction(instruction: Int, parameterPosition: Int): ParameterMode {
    if (parameterPosition <= 0) error(
      "parameter position should be 1 based but instead got $parameterPosition"
    )
    val modes = shift(instruction, 2)
    val mode = shift(modes, parameterPosition - 1) % 10
    return when (mode) {
      0 -> ParameterMode.POSITION
      1 -> ParameterMode.IMMEDIATE
      else -> error("unknown mode $mode")
    }
  }

  private fun shift(value: Int, digits: Int): Int {
    assert(digits >= 0)
    return when (digits) {
      0 -> value
      else -> shift(
        value / 10, digits - 1
      )
    }
  }
}