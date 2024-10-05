class MyTask {

    var numberOfTasks = 1

    val maxNumberOfTasks = 6

    fun printTask(){
        println("This is task number: " + numberOfTasks)
    }
}

/**
 * Use this method to play around
 */
fun main(args: Array<String>) {

    val task = MyTask()

    //This should be possible
    //task.maxNumberOfTasks += 1

    //this is not possible:
    // task.maxNumberOfTasks

}