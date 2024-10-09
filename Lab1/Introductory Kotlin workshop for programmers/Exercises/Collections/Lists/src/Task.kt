fun handleImmutableList(messages: List<String>): List<String> {
    var newList = messages.toMutableList()

    newList[3] = "d"
    newList.removeAt(2)

    return newList.toList()
}

fun handleMutableList(messages: MutableList<String>): List<String> {
    messages[3] = "d"
    messages.removeAt(2)

    return messages.toList()

}

/**
 * A main function to play with
 */
fun main(args: Array<String>) {
    println(handleImmutableList(listOf("x")))
}