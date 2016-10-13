package jp.keita.kagurazaka.monaka.demo

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import jp.keita.kagurazaka.monaka.MonakaComponent
import jp.keita.kagurazaka.monaka.ViewBinder
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textChangedListener

class ValidatableEditTextComponent : MonakaComponent {
    var text: String by ViewBinder(
            get = { textInputEditText.text.toString() },
            set = { textInputEditText.setText(it) }
    )

    var validator: ((String) -> String?)? = null

    var errorMessage: String? = null
        private set

    val hasError: Boolean
        get() = errorMessage != null

    private lateinit var textInputEditText: TextInputEditText
    private lateinit var textInputLayout: TextInputLayout

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        textInputLayout = textInputLayout {
            id = R.id.text_input_layout

            textInputEditText = textInputEditText {
                id = R.id.text_input_edit_text

                textChangedListener {
                    afterTextChanged {
                        errorMessage(validator?.invoke(it?.toString() ?: ""))
                    }
                }
            }
        }
        textInputLayout
    }

    fun clear() {
        val backup = validator
        validator = null

        text = ""
        errorMessage(null)

        validator = backup
    }

    private fun errorMessage(message: String?) {
        if (message != null) {
            errorMessage = message
            textInputLayout.error = message
            textInputLayout.isErrorEnabled = true
        } else {
            errorMessage = null
            textInputLayout.error = null
            textInputLayout.isErrorEnabled = false
        }
    }
}
