interface Animal

class Dog : Animal

class Carrier<T>(val thing: T)

fun carryDogs(): Carrier<*>? {

    val dogCarrier: Carrier<Dog> = Carrier(Dog())

    //FIXME: assign the dogCarrier to a new variable "myCarrier". The following doesn't work:
//var myCarrier: Carrier<Animal>= dogCarrier 

//Temporary placeholder:
var myCarrier: Carrier<*>? = null

    return myCarrier
}



