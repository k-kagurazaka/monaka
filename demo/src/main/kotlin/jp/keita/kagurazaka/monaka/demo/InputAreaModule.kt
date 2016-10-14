package jp.keita.kagurazaka.monaka.demo

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Button
import jp.keita.kagurazaka.monaka.MonakaStateComponent
import jp.keita.kagurazaka.monaka.component
import org.jetbrains.anko.*
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.addTo
import rx.subscriptions.CompositeSubscription

data class ButtonNameChanged(val newName: String) : Event

class InputAreaModule : MonakaStateComponent {
    // View
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

            button = button("Button") {
                onClick { onButtonClick?.invoke() }
            }.lparams {
                weight = 0.0f
            }
        }
    }

    override fun onAttachedToWindow() {
        subscriptions = CompositeSubscription()

        EventEmitter.observe()
                .ofType(ButtonNameChanged::class.java)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    button.text = it.newName
                }
                .addTo(subscriptions)
    }

    override fun onDetachedFromWindow() {
        subscriptions.unsubscribe()
    }

    // Component interface
    var inputText: String
        get() = editTextComponent.text
        set(value) {
            editTextComponent.text = value
        }

    fun onButtonClick(listener: () -> Unit) {
        onButtonClick = listener
    }

    fun clearInputArea() {
        editTextComponent.clear()
    }

    // Internal state
    private lateinit var editTextComponent: ValidatableEditTextComponent
    private lateinit var button: Button
    private var onButtonClick: (() -> Unit)? = null
    private lateinit var subscriptions: CompositeSubscription
}
