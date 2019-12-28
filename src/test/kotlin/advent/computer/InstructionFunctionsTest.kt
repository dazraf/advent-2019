package advent.computer

import advent.computer.InstructionFunctions.modeFromInstruction
import advent.computer.InstructionFunctions.opCodeFromInstruction
import advent.computer.Mode.IMMEDIATE
import advent.computer.Mode.POSITION
import org.junit.Assert.*
import org.junit.Test

class InstructionFunctionsTest {
  @Test
  fun `that a modeless instruction returns position`() {
    val instruction = 2
    assertEquals(2, opCodeFromInstruction(instruction))
    assertEquals(POSITION, modeFromInstruction(instruction, 3))
  }

  @Test
  fun `that an instruction with a mode returns the right mode for a parameter`() {
    val instruction = 1002
    assertEquals(2, opCodeFromInstruction(instruction))
    assertEquals(POSITION, modeFromInstruction(instruction, 1))
    assertEquals(IMMEDIATE, modeFromInstruction(instruction, 2))
  }

  @Test
  fun `that an illegal mode throws`() {
    val instruction = 202
    assertEquals(2, opCodeFromInstruction(instruction))
    assertThrows("unknown mode 2") {
      modeFromInstruction(instruction, 1)
    }
  }

  private fun assertThrows(
    message: String? = null, exceptionClass: Class<*> = Throwable::class.java,
    fn: () -> Unit
  ) {
    try {
      fn()
    } catch (er: Throwable) {
      assertTrue(
        "should throw exception of type $exceptionClass", exceptionClass.isInstance(er)
      )
      if (message != null) {
        assertEquals("should throw with message $message", message, er.message)
      }
      return
    }
    fail(
      "should have thrown an exception (message=$message exceptionClass=$exceptionClass)"
    )
  }
}
