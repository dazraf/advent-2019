package advent.day7

import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/**
 * A simple channel construct, presenting a queue as both a sequence for reading and
 * a writer function
 */
class Channel {

  companion object {
    fun channel() = Channel()
  }

  private val queue = LinkedBlockingQueue<Int>()
  val reader = generateSequence {
    queue.take()
  }

  fun register(fn: (Int) -> Unit) {
    // dirty - we should have a mechanism to stop this thread when its no longer required
    thread(isDaemon = true) {
      reader.forEach(fn)
    }
  }

  val writer = { value: Int ->
    queue.put(value)
  }
}