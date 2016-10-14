package jp.keita.kagurazaka.monaka

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ViewBinder<in R, T>(
        private val get: R.() -> T,
        private val set: R.(T) -> Unit
) : ReadWriteProperty<R, T> {
    override fun getValue(thisRef: R, property: KProperty<*>): T = thisRef.get()

    override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        if (thisRef.get() != value) {
            thisRef.set(value)
        }
    }
}
