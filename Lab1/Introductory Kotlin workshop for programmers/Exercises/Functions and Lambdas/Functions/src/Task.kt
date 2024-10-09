fun add(vararg numbers:Int) : Int{
    var sum = 0
    for (i in numbers){
        if (i % 2 == 0){
            sum+=i
        }
    }

    return sum
}

/**
 * main method to play with
 */
fun main() {
    //println( add(1,2,3,4) )
}
