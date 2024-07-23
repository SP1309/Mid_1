package com.example.midterm_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var currentOperation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val numberButtonIDs = arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        val numberClickListener = View.OnClickListener { view ->
            val button = view as Button
            if (isNewOperation) {
                display.text = button.text
                isNewOperation = false
            } else {
                display.append(button.text)
            }
        }

        for (id in numberButtonIDs) {
            findViewById<Button>(id).setOnClickListener(numberClickListener)
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener(operationClickListener)
        findViewById<Button>(R.id.btnSubtract).setOnClickListener(operationClickListener)
        findViewById<Button>(R.id.btnMultiply).setOnClickListener(operationClickListener)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(operationClickListener)
        findViewById<Button>(R.id.btnEquals).setOnClickListener(equalsClickListener)
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            display.text = "0"
            firstNumber = 0.0
            secondNumber = 0.0
            currentOperation = ""
            isNewOperation = true
        }

        // Handle custom intent
        intent?.let {
            if (it.action == "com.example.simplecalculator.CALCULATE") {
                it.getStringExtra(Intent.EXTRA_TEXT)?.let { data ->
                    display.text = data
                }
            }
        }

        findViewById<Button>(R.id.btnOpenSecondActivity).setOnClickListener {
            val intent = Intent(this, AppTwoActivity::class.java)
            startActivity(intent)
        }
    }

    private val operationClickListener = View.OnClickListener { view ->
        val button = view as Button
        currentOperation = button.text.toString()
        firstNumber = display.text.toString().toDouble()
        isNewOperation = true
    }

    private val equalsClickListener = View.OnClickListener {
        secondNumber = display.text.toString().toDouble()
        val result = when (currentOperation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else Double.NaN
            else -> 0.0
        }
        display.text = if (result.isNaN()) "Error" else result.toString()
        isNewOperation = true
    }
}
