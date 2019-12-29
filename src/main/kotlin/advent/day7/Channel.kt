package advent.day7

import java.util.concurrent.LinkedBlockingQueue

/**
 * A simple channel construct, presenting a queue as both a sequence for reading and
 * a writer function
 */
class Channel {

  private val queue = LinkedBlockingQueue<Int>()
  fun reader(): Sequence<Int> {
    return generateSequence {
      queue.take()
    }
  }

  fun writer(): (Int) -> Unit {
    return {
      queue.put(it)
    }
  }
}