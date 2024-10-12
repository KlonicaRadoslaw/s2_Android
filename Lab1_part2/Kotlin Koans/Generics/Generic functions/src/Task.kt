import java.util.*

fun <T, C1 : MutableCollection<in T>, C2 : MutableCollection<in T>>
        Iterable<T>.partitionTo(
        destination1: C1,
        destination2: C2,
        predicate: (T) -> Boolean
): Pair<C1, C2> {
    for (item in this) {
        if (predicate(item)) {
            destination1.add(item)
        } else {
            destination2.add(item)
        }
    }
    return Pair(destination1, destination2)
}

fun partitionWordsAndLines() {
    val (words, lines) = listOf("a", "a b", "c", "d e")
            .partitionTo(ArrayList(), ArrayList()) { s -> !s.contains(" ") }
    check(words == listOf("a", "c"))
    check(lines == listOf("a b", "d e"))
}

fun partitionLettersAndOtherSymbols() {
    val (letters, other) = setOf('a', '%', 'r', '}')
            .partitionTo(HashSet(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
    check(letters == setOf('a', 'r'))
    check(other == setOf('%', '}'))
}
