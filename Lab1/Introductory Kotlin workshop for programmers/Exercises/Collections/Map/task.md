#Maps

Just as List, a Map is a type of collection that can be mutable or immutable.
This collection holds pairs of objects (*keys* and *values*).
Instantiation is done through the methods `mapOf()` and `mutableMapOf()`.

````kotlin
    //pretty idiom for creating a map
    val myMap = mutableMapOf("a" to "apple", "b" to "bee")

    //less pretty, more efficient
    val myOtherMap = mutableMapOf(
            Pair("a", "apple"),
            Pair("b", "bee")
    )

    println(myMap["a"])

    myMap["a"] = "Adam"
    myMap["z"] = "zoolander"
    myMap.remove("b")

````

##Assignment

We will more or less do the something that we did for List, but now for Map:

There are two methods one receiving a mutable Map, the other an immutable Map.

Both methods should return an immutable Map that has the following changes:
- change the value for item with key "x" to 345
- remove the item with key "z"

For a complete list of all Map methods: [doc](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)
