class GarbageCollector {
    var organic = 0
    var recyclable = 0
    var chemicals = 0
    var burnables = 0

    fun collectGarbage(garbage: List<Garbage>) {
      //implement me
    }

}

interface Garbage {
    val collectible: Boolean
}

data class Plastic(override val collectible: Boolean = true) : Garbage
data class Green(override val collectible: Boolean = true) : Garbage
data class Paper(override val collectible: Boolean = false) : Garbage
data class Glass(override val collectible: Boolean = true) : Garbage
data class Chemicals(override val collectible: Boolean = false) : Garbage
data class Oil(override val collectible: Boolean = false) : Garbage
data class Batteries(override val collectible: Boolean = true) : Garbage




