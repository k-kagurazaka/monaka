package jp.keita.kagurazaka.monaka

import android.content.Context
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
