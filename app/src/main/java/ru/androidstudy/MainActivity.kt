package ru.androidstudy

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val buttonAdd by lazy { findViewById<Button>(R.id.buttonAdd) }
    private val buttonSubtract by lazy { findViewById<Button>(R.id.buttonSubtract) }
    private val buttonDivide by lazy { findViewById<Button>(R.id.buttonDivide) }
    private val buttonMultiply by lazy { findViewById<Button>(R.id.buttonMultiply) }
    private val buttonClean by lazy { findViewById<Button>(R.id.buttonClean) }

    private val result by lazy { findViewById<TextView>(R.id.result) }
    private val operation by lazy { findViewById<TextView>(R.id.operation) }
    private val number1 by lazy { findViewById<EditText>(R.id.number1) }
    private val number2 by lazy { findViewById<EditText>(R.id.number2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonAdd.setOnClickListener(this)
        buttonSubtract.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonClean.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var res = 0f
        val num1 = number1.text.toString().toFloatOrNull() ?: 0f
        val num2 = number2.text.toString().toFloatOrNull() ?: 0f
        when (v?.id) {
            R.id.buttonAdd -> {
                operation.text = "+"
                res = num1 + num2
            }
            R.id.buttonSubtract -> {
                operation.text = "-"
                res = num1 - num2
            }
            R.id.buttonMultiply -> {
                operation.text = "*"
                res = num1 * num2
            }
            R.id.buttonDivide -> {
                operation.text = "/"
                res = num1 / num2
            }
            R.id.buttonClean -> {
                number1.setText("")
                operation.text = ""
                number2.setText("")
                result.text = ""
            }
        }
        result.text = res.toString()
    }
}