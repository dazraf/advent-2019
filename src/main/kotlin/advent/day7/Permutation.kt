package advent.day7

/**
 * Simple, recursive approach for generating permutations of a list
 */
fun <T> permutation(items: List<T>): Sequence<List<T>> {
  return sequence {
    when {
      items.size <= 1 -> yield(items)
      else -> {
        items.forEachIndexed { pivot, item ->
          // for each item
          val remaining =
            items.filterIndexed { idx, _ -> idx != pivot } // remove that item
          permutation(remaining) // permute the remaining items
            .forEach { remainingPermutation ->
              // and for each permutation ...
              // prepend the removed item, yielding another result
              yield(listOf(item) + remainingPermutation)
            }
        }
      }
    }
  }
}
