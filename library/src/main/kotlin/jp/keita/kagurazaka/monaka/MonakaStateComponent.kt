package jp.keita.kagurazaka.monaka

import android.content.Context
import android.os.Bundle
import android.view.ViewManager
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.*

/**
 * Interface to represent a component that consists of the view and presentation logic and has
 * a feature of keeping the internal state over reconstruction.
 */
interface MonakaStateComponent : AnkoComponent<Context> {
    fun onAttachedToWindow() {
    }

    fun onDetachedFromWindow() {
    }

    fun onSaveInstanceState(outState: Bundle) {
    }

    fun onRestoreInstanceState(state: Bundle) {
    }
}

inline fun <reified C : MonakaStateComponent>
        ViewManager.stateComponent(factory: () -> C, theme: Int = 0): MonakaView<C>
        = stateComponent(factory, theme) {}

inline fun <reified C : MonakaStateComponent>
        ViewManager.stateComponent(factory: () -> C, init: C.() -> Unit): MonakaView<C>
        = stateComponent(factory, 0, init)

inline fun <reified C : MonakaStateComponent>
        ViewManager.stateComponent(factory: () -> C, theme: Int, init: C.() -> Unit): MonakaView<C>
        = ankoView({ MonakaView<C>(it).apply { component = factory() } }, theme) { component?.init() }
