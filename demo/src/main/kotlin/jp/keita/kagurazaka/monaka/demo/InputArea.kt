package jp.keita.kagurazaka.monaka.demo

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.EditText
import jp.keita.kagurazaka.monaka.MonakaComponent
import org.jetbrains.anko.*

class InputArea : MonakaComponent {
    var inputText: String by ViewProperty(
            get = { inputTextView.text.toString() },
            set = { inputTextView.setText(it) }
    ) {}

    private lateinit var inputTextView: EditText

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent) {
                gravity = Gravity.CENTER_VERTICAL
            }
            inputTextView = editText().lparams(width = dip(0)) {
                weight = 1.0f
            }
            button("Add") {
                onClick { onAddButtonClick?.invoke(it) }
            }.lparams {
                weight = 0.0f
            }
        }
    }

    private var onAddButtonClick: ((View?) -> Unit)? = null
    fun onAddButtonClick(listener: (View?) -> Unit) {
        onAddButtonClick = listener
    }
}
