//Implement MyNum with infix here
class MyNum{
    var number = 0

    constructor(number: Int){
        this.number = number
    }

    infix fun add(num1: Int){
        this.number = this.number + num1
    }
}


/**
 * Main method to play with
 */
fun main(args: Array<String>) {
//     val someNumber = MyNum(4345)
//     println( someNumber add 4 )
}