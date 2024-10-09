import kotlin.system.measureTimeMillis

object Stopwatch {

    /**
     * Measures execution time in ms of a given callback
     *
     *  @param block the function to measure
     *  @return time elapsed = end time - start time
     *
     */
    //implement elapse function
    fun elapse(block:() -> Unit) : Long{
        return measureTimeMillis(block)
    }
}

fun main() {

    val init: (Int) -> Int = { i -> i * i }
    val numbers = Array(100000, init)

    Stopwatch.elapse { numbers.forEach { i -> println(i) } }
}
