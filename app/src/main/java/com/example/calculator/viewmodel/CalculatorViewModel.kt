package com.example.calculator.viewmodel

import androidx.lifecycle.ViewModel
import com.example.calculator.evaluateExpression
import com.example.calculator.interfaces.ICalculatorViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculatorViewModel : ViewModel(), ICalculatorViewModel {
    private val _expression = MutableStateFlow("")
    override val expression: StateFlow<String> get() = _expression

    private val _result = MutableStateFlow("")
    override val result: StateFlow<String> get() = _result

    override fun onButtonClick(symbol: String) {
        when (symbol) {
            "C" -> {
                _expression.value = ""
                _result.value = ""
            }

            "=" -> {
                _result.value = try {
                    evaluateExpression(_expression.value)
                } catch (e: Exception) {
                    "Error"
                }
            }

            else -> _expression.value += symbol
        }
    }
}