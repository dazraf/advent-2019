package advent.computer

/**
 * Simple memory model implementation
 */
class Memory(memory: IntArray) {

  // this is probably excessive - experimenting with programs that wish to access memory
  // beyond their size. however, copying is a good thing as we later find out on day 7 ;-)
  private val ram =
    memory.mapIndexed { index, value -> index to value }.toMap().toMutableMap()

  operator fun get(address: Int) = ram[address] ?: 0
  operator fun set(address: Int, value: Int) {
    ram[address] = value
  }
}

