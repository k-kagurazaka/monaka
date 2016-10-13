package jp.keita.kagurazaka.monaka.demo

import android.support.design.widget.TextInputEditText
import android.view.ViewManager
import org.jetbrains.anko.custom.*

inline fun ViewManager.textInputEditText() = textInputEditText {}
inline fun ViewManager.textInputEditText(theme: Int = 0, init: TextInputEditText.() -> Unit)
        = ankoView(::TextInputEditText, theme, init)
