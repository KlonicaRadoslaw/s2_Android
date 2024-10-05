package nl.dali.testutils

import org.junit.Test
import kotlin.reflect.KClass


fun getClass(className: String): Class<*>? {
    return try {
        Class.forName(className, false, Test::class.java.classLoader)!!
    } catch (e: ClassNotFoundException) {
        null
    }
}

fun getObject(className: String): KClass<*>? {
    return try {
        Class.forName(className, false, Test::class.java.classLoader)!!.kotlin
    } catch (e: ClassNotFoundException) {
        null
    }
}


fun String.toClass(): Class<*>? = getClass(this)
fun String.toObject(): KClass<*>? = getObject(this)
