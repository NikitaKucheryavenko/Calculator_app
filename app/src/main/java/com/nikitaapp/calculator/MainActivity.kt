package com.nikitaapp.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var textOperation: TextView
    private var isNewOp = true
    private var operation = "*"
    private var oldNumber = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        textOperation = findViewById(R.id.textOperation)
    }

    fun btnClick(view: View) {
        val selectedButton = view as Button
        var entryData = editTextNumber.text.toString()

        if (isNewOp) {
            editTextNumber.setText("")
            isNewOp = false
        }

        when (selectedButton.id) {
            R.id.btnAC -> {
                entryData = "0"
                isNewOp = true
            }
            R.id.btnBack -> {
                if (entryData.length > 1) {
                    entryData = entryData.substring(0, entryData.length - 1)
                } else {
                    entryData = "0"
                }
            }
            R.id.btnPercent -> {
                if (entryData.last() != '%') {
                    entryData += "%"
                }
            }
            R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn17 -> {
                if (entryData == "0") entryData = ""
                entryData += (selectedButton.text)
            }
            R.id.btnDot -> {
                if (!entryData.contains(".")) {
                    entryData += "."
                }
            }
        }
        editTextNumber.setText(entryData)
    }

    fun btnOpClick(view: View) {
        val selectedButton = view as Button
        operation = when (selectedButton.id) {
            R.id.btnPlus -> "+"
            R.id.btnMinus -> "-"
            R.id.btnDivide -> "/"
            R.id.btnMultiple -> "*"
            else -> operation
        }
        oldNumber = editTextNumber.text.toString()
        editTextNumber.setText("")
        textOperation.setText(operation)
        isNewOp = true
    }

    fun btnEqualEvent(_view: View) {
        var newNumber = editTextNumber.text.toString()
        var result: Double? = null
        when (operation) {
            "+" -> {
                result = oldNumber.toDouble() + newNumber.toDouble()
            }
            "-" -> {
                result = oldNumber.toDouble() - newNumber.toDouble()
            }
            "*" -> {
                result = oldNumber.toDouble() * newNumber.toDouble()
            }
            "/" -> {
                result = oldNumber.toDouble() / newNumber.toDouble()
            }
        }
        val resultString = result.toString()
        editTextNumber.setText(if (resultString.endsWith(".0")) resultString.substring(0, resultString.length - 2) else resultString)
        isNewOp = true
    }
}