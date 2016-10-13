package jp.keita.kagurazaka.monaka.demo

import android.content.Context
import android.view.Gravity
import android.view.View
import jp.keita.kagurazaka.monaka.MonakaComponent
import jp.keita.kagurazaka.monaka.component
import org.jetbrains.anko.*

class InputArea : MonakaComponent {
    var inputText: String
        get() = editTextComponent.text
        set(value) {
            editTextComponent.text = value
        }

    private lateinit var editTextComponent: ValidatableEditTextComponent

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent) {
                gravity = Gravity.CENTER_VERTICAL
            }

            component(::ValidatableEditTextComponent) {
                editTextComponent = this

                validator = { if (it.isBlank()) "Required" else null }
            }.lparams(width = dip(0)) {
                weight = 1.0f
            }

            button("Add") {
                onClick { editTextComponent.clear() }
            }.lparams {
                weight = 0.0f
            }
        }
    }
}
