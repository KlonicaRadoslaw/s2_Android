class Plumbus private constructor(val id: Int) {
    companion object PlumbusFactory{
        private var count = 0
        fun create() : Plumbus{
            count++
            return Plumbus(count)
        }
    }
}


fun main() {
//    Plumbus.create()
//    Plumbus.create()
//    Plumbus.create()
//    Plumbus.create()
//    val myFirstPlumbus = Plumbus.create()
//    println("My first plumbus id: ${myFirstPlumbus.id}")
//    println("Total production cound: ${Plumbus.count}")

//    myFirstPlumbus.create() //not possible
}
