package ru.androidstudy

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val btnPaint by lazy { findViewById<ImageButton>(R.id.btnPaint) }
    private val btnText by lazy { findViewById<ImageButton>(R.id.btnText) }
    private val btnClear by lazy { findViewById<ImageButton>(R.id.btnClear) }

    private val editText by lazy { findViewById<EditText>(R.id.editText) }

    private val paintArea by lazy { findViewById<PaintArea>(R.id.paint) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPaint.setOnClickListener { view -> btnPaintClick(view) }
        btnText.setOnClickListener { view -> btnTextClick(view) }
        btnClear.setOnClickListener { view -> btnClearClick(view) }
        paintArea.visibility = View.INVISIBLE
    }

    private fun btnPaintClick(view: View) {
        paintArea.visibility = View.VISIBLE
        editText.clearFocus()
    }

    private fun btnTextClick(view: View) {
        editText.visibility = View.VISIBLE
        editText.requestFocus()
    }

    private fun btnClearClick(view: View) {
        paintArea.cleanPaintArea()
        editText.visibility = View.INVISIBLE
        editText.text.clear()
        paintArea.invalidate()
    }

}