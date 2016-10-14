package jp.keita.kagurazaka.monaka

import com.nhaarman.mockito_kotlin.*
import com.taroid.knit.should
import org.junit.Test

class ViewBinderTest {
    interface Property {
        fun get(): Int
        fun set(value: Int)
    }

    var property: Int by ViewBinder(
            get = { this.mock.get() },
            set = { this.mock.set(it) }
    )

    private val mock = mock<Property> {
        on { get() } doReturn 1822
    }

    @Test
    fun when_property_getter_called() {
        property.should be 1822

        verify(mock).get()
    }

    @Test
    fun when_property_setter_called() {
        property = 15

        verify(mock).set(15)
    }

    @Test
    fun weh_property_setter_called_with_the_same_value() {
        property = 1822

        verify(mock, never()).set(any())
    }
}
