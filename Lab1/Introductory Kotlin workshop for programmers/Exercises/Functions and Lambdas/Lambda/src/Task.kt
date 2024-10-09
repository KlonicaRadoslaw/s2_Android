fun add(vararg numbers:Int):Int{

 numbers.filter { it -> it%2==0 }

  return numbers.sum()
}

/**
 * main method to play with
 */
fun main(args: Array<String>) {
    //println( add(1,2,3,4) )
}
