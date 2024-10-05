open class Point{
    public var name = ""

    constructor(name:String){
        this.name = name
    }

    public open fun nrOfCorners(): Int {
        return 0
    }
}

class Line(name: String) : Point(name){
    override fun nrOfCorners(): Int {
        return 2
    }
}

class Square(name: String) : Point(name){
    override fun nrOfCorners(): Int {
        return 4
    }
}

/**
 * Main method to play with
 */
fun main(args: Array<String>) {
}