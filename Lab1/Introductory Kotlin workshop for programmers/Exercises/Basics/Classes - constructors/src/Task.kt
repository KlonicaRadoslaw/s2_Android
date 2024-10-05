class Car{
    private var numberOfWheels = 0
    private var color = "red"

    constructor(numberOfWheels:Int){
        this.numberOfWheels = numberOfWheels
    }

    constructor(numberOfWheels:Int, color:String){
        this.numberOfWheels = numberOfWheels
        this.color = color
    }
}

/**
 * Main function to play around with
 */
fun main(args: Array<String>) {
    val car = Car(12,"red")
    val car2 = Car(12)
}