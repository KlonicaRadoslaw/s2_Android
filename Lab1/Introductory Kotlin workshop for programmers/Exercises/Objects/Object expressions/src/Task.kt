class Route {

    fun reply (inMessage: Message<In>) = object : Message<Out> {
        override fun body() = Out(inMessage.body().value, status = "success")
    }
}

data class Out(val inValue: String, val status: String)
data class In(val value: String)

interface Message<T> {
    fun body(): T
}