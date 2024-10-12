import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class D {
    var date: MyDate by EffectiveDate()
}

class EffectiveDate : ReadWriteProperty<Any?, MyDate> {

    private var timeInMillis: Long? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): MyDate {
        return timeInMillis?.toDate() ?: throw IllegalStateException("Date not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: MyDate) {
        timeInMillis = value.toMillis()
    }
}
