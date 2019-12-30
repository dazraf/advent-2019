package advent.day7

import advent.computer.Computer
import advent.day7.Channel.Companion.channel
import kotlin.concurrent.thread

/**
 * A container for a [Computer] which runs in its own thread
 * and provides I/O channels
 */
class ThreadedComputer(program: IntArray, haltHandler: () -> Unit) {

  companion object {
    fun computer(program: IntArray, haltHandler: () -> Unit) =
      ThreadedComputer(program, haltHandler)
  }

  val inChannel = channel()
  val outChannel = channel()
  val computer = Computer(
    program = program, haltHandler = haltHandler, input = inChannel.reader.iterator(),
    output = outChannel.writer
  )

  init {
    // dirty - we should be signalling the thread to interrupt and shutdown
    thread(isDaemon = true) {
      computer.runProgram()
    }
  }

  fun write(value: Int) {
    inChannel.writer(value)
  }
}

/**
 * binds the output stream from [this] to the input stream of [rhs]
 */
infix fun ThreadedComputer.bind(rhs: ThreadedComputer): ThreadedComputer {
  val writer = rhs.inChannel.writer
  outChannel.register(writer::invoke)
  return rhs
}

/**
 * binds the output stream of [this] to the [rhs] function
 */
infix fun ThreadedComputer.bind(rhs: (Int) -> Unit) {
  outChannel.register(rhs)
}

