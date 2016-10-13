package jp.keita.kagurazaka.monaka

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.FrameLayout
import org.jetbrains.anko.*

class MonakaView<C : MonakaStateComponent> : FrameLayout {
    var component: C? = null
        set(value) {
            removeAllViews()
            if (value != null) {
                addView(value.createView(context.UI { }))
            }
            field = value
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
    : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        component?.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        component?.onDetachedFromWindow()
    }

    override fun onSaveInstanceState(): Parcelable = Bundle().apply {
        putParcelable(KEY_STATE_SUPER, super.onSaveInstanceState())
        component?.onSaveInstanceState(this)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            component?.onRestoreInstanceState(state)
            super.onRestoreInstanceState(state.getParcelable(KEY_STATE_SUPER))
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    companion object {
        private val KEY_STATE_SUPER = "key_state_super"
    }
}
