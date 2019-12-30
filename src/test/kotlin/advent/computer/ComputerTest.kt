package advent.computer

import org.junit.Assert.assertEquals
import org.junit.Test

class ComputerTest {
  @Test
  fun `that we can add two numbers in immediate mode`() {
    val p1 = 2
    val p2 = 5
    val resultLocation = 5
    val program = intArrayOf(1101, p1, p2, resultLocation, 99, 0)
    val memory = Computer.runProgram(program).memory
    assertEquals(p1 + p2, memory[resultLocation])
  }

  @Test
  fun `that we can multiply two numbers in immediate mode`() {
    val p1 = 2
    val p2 = 5
    val resultLocation = 5
    val program = intArrayOf(1102, p1, p2, resultLocation, 99, 0)
    val memory = Computer.runProgram(program).memory
    assertEquals(p1 * p2, memory[resultLocation])
  }

  @Test
  fun `that we can add two numbers in mixed modes`() {
    val p1 = 2
    val p2 = 5
    val resultLocation = 6
    val program = intArrayOf(1001, 5, p2, resultLocation, 99, p1, 0)
    val memory = Computer.runProgram(program).memory
    assertEquals(p1 + p2, memory[resultLocation])
  }

  @Test
  fun `that we can multiply two numbers in mixed modes`() {
    val p1 = 2
    val p2 = 5
    val resultLocation = 6
    val program = intArrayOf(1002, 5, p2, resultLocation, 99, p1, 0)
    val memory = Computer.runProgram(program).memory
    assertEquals(p1 * p2, memory[resultLocation])
  }
}