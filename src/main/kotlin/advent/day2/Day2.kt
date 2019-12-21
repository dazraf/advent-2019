package advent.day2

import kotlin.system.exitProcess


fun main() {
    for (noun in 0..99) {
        for (verb in 0..99) {
            val memory = getProgram()
            memory[1] = noun
            memory[2] = verb
            runProgram(memory)
            if (memory[0] == 19690720) {
                println(100 * noun + verb)
                exitProcess(0)
            }
        }
    }
}

private fun getProgram() =
    intArrayOf(
        1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 19, 1, 13, 19, 23, 2, 23, 9, 27, 1, 6, 27, 31, 2, 10,
        31, 35, 1, 6, 35, 39, 2, 9, 39, 43, 1, 5, 43, 47, 2, 47, 13, 51, 2, 51, 10, 55, 1, 55, 5, 59, 1, 59, 9, 63, 1,
        63, 9, 67, 2, 6, 67, 71, 1, 5, 71, 75, 1, 75, 6, 79, 1, 6, 79, 83, 1, 83, 9, 87, 2, 87, 10, 91, 2, 91, 10, 95,
        1, 95, 5, 99, 1, 99, 13, 103, 2, 103, 9, 107, 1, 6, 107, 111, 1, 111, 5, 115, 1, 115, 2, 119, 1, 5, 119, 0, 99,
        2, 0, 14, 0
    )

private fun runProgram(memory: IntArray) {
    generateSequence(0) {
        when (memory[it]) {
            99 -> null
            else -> processOpCode(memory, it)
        }
    }.last()
}

fun processOpCode(memory: IntArray, ip: Int): Int {
    return when (val opCode = memory[ip]) {
        1 -> opAdd(memory, ip)
        2 -> opMultiply(memory, ip)
        else -> error("unknown opcode $opCode at position $ip")
    }.also { memory.println() }
}

fun opAdd(memory: IntArray, ip: Int): Int {
    val lhs = memory[ip + 1]
    val rhs = memory[ip + 2]
    val r = memory[ip + 3]
    memory[r] = memory[lhs] + memory[rhs]
    return ip + 4
}

fun opMultiply(memory: IntArray, ip: Int): Int {
    val lhs = memory[ip + 1]
    val rhs = memory[ip + 2]
    val r = memory[ip + 3]
    memory[r] = memory[lhs] * memory[rhs]
    return ip + 4
}

fun IntArray.println() {
    println(joinToString(",") { it.toString() })
}

private fun loadProgram(): IntArray {
    val line = readLine() ?: error("null line read")
    return line.split(',').map { it.trim() }.map { it.toInt() }.toIntArray()
}

