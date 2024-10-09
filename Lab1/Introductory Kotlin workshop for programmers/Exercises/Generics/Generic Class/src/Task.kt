//Implement Transport class here
class Transport<Animal>(val goods: Animal)

interface Animal
class Dog:Animal
class Cat:Animal


fun <T:Animal> catTransporter() {}

fun main() {
}
