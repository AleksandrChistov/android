package ru.androidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val helloText by lazy { findViewById<TextView>(R.id.helloText) }
    private val startButton by lazy { findViewById<ImageButton>(R.id.startButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartButtonClick(view: View) {
        startButton.setImageResource(R.drawable.ic_launcher_foreground)
        helloText.visibility = View.VISIBLE
        startButton.isClickable = false
    }
}