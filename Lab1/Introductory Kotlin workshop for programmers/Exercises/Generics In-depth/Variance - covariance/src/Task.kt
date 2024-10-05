interface Animal

class Dog : Animal
class Carrier<T>(private var thing: T){
    private var name:String = "unnamed"
    fun getThing():T = thing

    fun addThing(thing:T) { this.thing = thing}
    fun changeName(newName:String) { name = newName }

}


class UseCase {
    var animalCarrier: Carrier<Animal>? = null
    val dogCarrier: Carrier<Dog> = Carrier(Dog())

    fun assignCarrier() {
//Assign a dog Carrier to the animal carrier here
    }
}
