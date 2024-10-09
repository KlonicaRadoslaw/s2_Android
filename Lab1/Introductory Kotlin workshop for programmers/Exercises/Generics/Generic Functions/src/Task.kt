
class Cat
class Dog

//implement the method
fun <T> isAPet(item: T):Boolean {
    if (item is Cat || item is Dog)
        return true
    else
        return false
}