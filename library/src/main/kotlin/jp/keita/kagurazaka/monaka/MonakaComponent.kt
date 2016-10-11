package jp.keita.kagurazaka.monaka

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.*

/**
 * Marker interface to represent a component that consists of the view and presentation logic.
 */
interface MonakaComponent : AnkoComponent<Context>

inline fun <reified C : MonakaComponent>
        ViewManager.component(factory: () -> C, theme: Int = 0): View
        = component(factory, theme) {}

inline fun <reified C : MonakaComponent>
        ViewManager.component(factory: () -> C, init: C.() -> Unit): View
        = component(factory, 0, init)

inline fun <reified C : MonakaComponent>
        ViewManager.component(factory: () -> C, theme: Int = 0, init: C.() -> Unit): View {
    val component = factory()
    return ankoView({ component.createView(it.UI { }) }, theme) { component.init() }
}

/**
 * Interface to represent a component that consists of the view and presentation logic and has
 * a feature of keeping the internal state over reconstruction.
 */
interface WrappedMonakaComponent : MonakaComponent {
    fun onSaveInstanceState(outState: Bundle) {
    }

    fun onRestoreInstanceState(state: Bundle) {
    }
}

inline fun <reified C : WrappedMonakaComponent>
        ViewManager.wrappedComponent(factory: () -> C, theme: Int = 0): MonakaComponentWrapper<C>
        = wrappedComponent(factory, theme) {}

inline fun <reified C : WrappedMonakaComponent>
        ViewManager.wrappedComponent(factory: () -> C, init: C.() -> Unit): MonakaComponentWrapper<C>
        = wrappedComponent(factory, 0, init)

inline fun <reified C : WrappedMonakaComponent>
        ViewManager.wrappedComponent(factory: () -> C, theme: Int, init: C.() -> Unit): MonakaComponentWrapper<C>
        = ankoView({ MonakaComponentWrapper<C>(it).apply { component = factory() } }, theme) { component?.init() }
