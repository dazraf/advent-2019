package advent.day2

private fun getProgram() = intArrayOf(
    1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 19, 1, 13, 19, 23, 2, 23, 9, 27, 1, 6, 27, 31, 2, 10,
    31, 35, 1, 6, 35, 39, 2, 9, 39, 43, 1, 5, 43, 47, 2, 47, 13, 51, 2, 51, 10, 55, 1, 55, 5, 59, 1, 59, 9, 63, 1,
    63, 9, 67, 2, 6, 67, 71, 1, 5, 71, 75, 1, 75, 6, 79, 1, 6, 79, 83, 1, 83, 9, 87, 2, 87, 10, 91, 2, 91, 10, 95,
    1, 95, 5, 99, 1, 99, 13, 103, 2, 103, 9, 107, 1, 6, 107, 111, 1, 111, 5, 115, 1, 115, 2, 119, 1, 5, 119, 0, 99,
    2, 0, 14, 0
)

fun main() {
    val (noun, verb) = (0..99).asSequence().flatMap { noun -> (0..99).asSequence().map { verb -> noun to verb } }
        .first { (noun, verb) ->
            val memory = getProgram()
            memory[1] = noun
            memory[2] = verb
            runProgram(memory)
            memory[0] == 19690720
        }
    println(100 * noun + verb)
}

private fun runProgram(memory: IntArray) {
    generateSequence(0) {
        when (memory[it]) {
            99 -> null
            else -> processOpCode(memory, it)
        }
    }.last()
}

val ops = mapOf(
    1 to ::opAdd,
    2 to ::opMultiply
)

fun processOpCode(memory: IntArray, ip: Int): Int {
    val opCode = memory[ip]
    val op = ops[memory[ip]] ?: error("unknown opcode $opCode at position $ip")
    return op(memory, ip)
}

fun opAdd(memory: IntArray, ip: Int): Int {
    memory[memory[ip + 3]] = memory[memory[ip + 1]] + memory[memory[ip + 2]]
    return ip + 4
}

fun opMultiply(memory: IntArray, ip: Int): Int {
    memory[memory[ip + 3]] = memory[memory[ip + 1]] * memory[memory[ip + 2]]
    return ip + 4
}
