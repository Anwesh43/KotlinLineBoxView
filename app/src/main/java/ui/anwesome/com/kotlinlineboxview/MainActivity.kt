package ui.anwesome.com.kotlinlineboxview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.lineboxview.LineBoxView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view1 = LineBoxView.create(this, 200, 200)
        val view2 = LineBoxView.create(this, 300, 300)
        val view3 = LineBoxView.create(this, 300, 300)
        val view4 = LineBoxView.create(this, 200, 200)
        view1.x = 0f
        view2.x = 250f
        view3.x = 0f
        view3.y = 400f
        view4.y = 400f
        view4.x = 400f
    }
}
