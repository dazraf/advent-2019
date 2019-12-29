package advent.computer

enum class ParameterMode {
  /**
   * the raw parameter value is deferenced to the contents of a memory address
   */
  POSITION,
  /**
   * the raw parameter value is used
   */
  IMMEDIATE
}