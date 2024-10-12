import java.util.HashMap

fun buildMutableMap(build: HashMap<Int, String>.() -> Unit): HashMap<Int, String> {
    val map = HashMap<Int, String>()
    map.build()
    return map
}

fun usage(): Map<Int, String> {
    return buildMutableMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}
