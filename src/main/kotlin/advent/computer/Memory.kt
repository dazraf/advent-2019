package advent.computer

class Memory(memory: IntArray) {
  private val ram =
    memory.mapIndexed { index, value -> index to value }.toMap().toMutableMap()

  operator fun get(address: Int) = ram[address] ?: 0
  operator fun set(address: Int, value: Int) {
    ram[address] = value
  }
}

