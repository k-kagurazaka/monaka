package jp.keita.kagurazaka.monaka.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jp.keita.kagurazaka.monaka.component
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UI {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)
                component(::InputArea) {
                    onAddButtonClick {  }
                }
            }
        }
    }
}
