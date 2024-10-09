//Implement the oneMinus() extension for Array<Int>
fun Array<Int>.oneMinus() : Array<Int>{
    return this.map{ it -> 1-it}.toTypedArray()
}
/**
 * Usage example
 */
fun main() {
    val myArray = arrayOf(3, 4, 5)
    val minusArray: Array<Int> = myArray.oneMinus()

    minusArray.forEach { println("* $it") }
}


