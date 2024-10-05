package nl.dali.testutils

import org.junit.Assert
import org.junit.Assert.fail
import java.lang.reflect.Constructor
import java.lang.reflect.Method

fun Class<*>.assertIsDataClass() {
    val kClazz = this.kotlin
    Assert.assertTrue(
        "Mistake in class ${this.simpleName}: Missing the required data class keyword",
        kClazz::isData.get()
    )
}


fun Class<*>.assertIsObject() {
    val kClazz = this.kotlin
    Assert.assertNotNull("Mistake in class ${this.simpleName}: It should be an object", kClazz::objectInstance)
}

fun Class<*>.assertHasConstructorParam(vararg args: Class<out Any>) {
    try {
        this.getConstructor(*args)
    } catch (e: Exception) {
        val constructor = args.joinToString { arg -> arg.simpleName }
        fail("Mistake in class ${this.simpleName}: Missing the required constructor ($constructor)")
    }
}

fun Class<*>.assertHasPropertyAndReturn(valName: String, retType: Class<*>):Method {
    val firstChar = valName[0].uppercaseChar()
    val restOfVal = valName.substring(1)

    val getMethod = "get${firstChar}${restOfVal}"
    return try {
        val method = this.getMethod(getMethod)
        if (!retType.isAssignableFrom(method.returnType)) {
            fail{"Mistake in class ${this.simpleName}: Property \"${valName}\" is of type ${method.returnType}. Should be ${retType}"}
        }else{
            method
        }
    } catch (e: Exception) {
        fail{"Mistake in class ${this.simpleName}: Can't see any \"${valName}\" property. Did you forget to add 'var' or 'val'?"}
    }
}

fun Class<*>.assertHasFunction(functionName: String, retType: Class<*>, vararg paramTypes: Class<out Any>) {

    try {
        val method = this.getMethod(functionName, *paramTypes)
        if (method?.returnType != retType) {
            throw java.lang.RuntimeException()
        }
    } catch (ex: java.lang.Exception) {
        fail("""Mistake in class ${this.simpleName}: Cannot find a function with the name "$functionName". If you are sure you have a function with this name, please check its signature.""")
    }
}

fun Class<*>.getMethodOrNull(name: String, retType: Class<*>, vararg inputType: Class<*>): Method? {
    try {
        val method = this.getMethod(name, *inputType)!!
        if (retType.isAssignableFrom(method.returnType)) {
            return method
        }
    } catch (e: Exception) {
    }
    return null
}

fun <T : Any> Class<T>.construct(vararg args: Any): T {

    val parms = args.map { arg -> arg::class.java }.toTypedArray()
    try {
        return this.constructors
            .firstOrNull { constructor -> constructor.hasMatchingParams(*parms) }
            ?.newInstance(*args) as T?
            ?: fail{"No constructor found for class '${this.javaClass.simpleName}' with parameters [$parms]"}
    } catch (e: Exception) {
        fail {
            """Cannot invoke ${this.simpleName}: ${e::class.java.simpleName}: ${e.localizedMessage}.
                |Stacktrace: ${e.stackTraceToString()}
            """.trimMargin()
        }
    }
}

fun fail(msg: () -> String): Nothing = fail(msg()) as Nothing

private fun <T> Constructor<T>.hasMatchingParams(vararg parms: Class<out Any>): Boolean =
    if (parameterCount == parms.size) {
        parameters.foldIndexed(true) { index, acc, next ->
            acc && (next.type.isAssignableFrom(parms[index]))
        }
    } else {
        false
    }





