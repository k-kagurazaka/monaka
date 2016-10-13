package jp.keita.kagurazaka.monaka.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.keita.kagurazaka.monaka.component
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout {
            lparams(width = matchParent, height = matchParent)

            component(::InputArea).lparams(width = matchParent)
        }
    }
}
