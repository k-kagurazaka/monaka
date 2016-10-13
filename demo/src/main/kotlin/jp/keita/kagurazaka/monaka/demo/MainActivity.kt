package jp.keita.kagurazaka.monaka.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.keita.kagurazaka.monaka.stateComponent
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout {
            lparams(width = matchParent, height = matchParent)

            stateComponent(::InputAreaModule) {
                onButtonClick {
                    EventEmitter.dispatch(ButtonNameChanged(inputText))
                    clearInputArea()
                }
            }.lparams(width = matchParent)
        }
    }
}
