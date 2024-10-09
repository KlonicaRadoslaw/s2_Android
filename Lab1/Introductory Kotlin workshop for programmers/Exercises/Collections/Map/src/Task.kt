fun handleImmutableMap(messages: Map<String,Int>): Map<String,Int> {
    var newMap = messages.toMutableMap()

    newMap["z"] = 345
    newMap.remove("z")

    return newMap.toMap()
}

fun handleMutableMap(messages: MutableMap<String,Int>): Map<String,Int> {
    messages["z"] = 345
    messages.remove("z")

    return messages.toMap()
}

/**
 * A main function to play with
 */
fun main(args: Array<String>) {
    println(handleImmutableMap(mapOf("m" to 123)))
}