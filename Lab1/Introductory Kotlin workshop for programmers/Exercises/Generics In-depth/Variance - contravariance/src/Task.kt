abstract class Dog(val name: String)
class DanishDog(name: String) : Dog(name)

open class Trainer<in T> {

    open fun train(target: T) {
        throw NotImplementedError()
    }

}

class UseCase {
    var danishDogTrainer: Trainer<DanishDog>? = null

    fun assignTrainer() {
        val dogTrainer = object : Trainer<Dog>() {
            override fun train(target: Dog) {
                println("Dog ${target.name} is trained")
            }
        }

        //Assign the generic dog trainer to the danish dog trainer
        danishDogTrainer = dogTrainer
    }
}
