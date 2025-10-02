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
            "⌫" -> {
                if (_expression.value.isNotEmpty()) {
                    _expression.value = _expression.value.dropLast(1)
                }
            }

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

            else -> {
                val operators = listOf("+", "-", "×", "*", "÷", "/")

                if (symbol in operators) {
                    val expr = _expression.value

                    if (symbol == "-") {
                        if (expr.isEmpty() || expr.last()
                                .toString() in operators || expr.last() == '('
                        ) {
                            _expression.value += symbol
                            return
                        }
                    }

                    if (_expression.value.isEmpty()) {
                        return
                    }

                    val lastChar = _expression.value.last().toString()
                    if (lastChar in operators) {
                        _expression.value =
                            _expression.value.dropLast(1) + symbol
                    }
                    else {
                        _expression.value += symbol
                    }
                } else {
                    if (symbol == ".") {
                        val parts = _expression.value.split(Regex("[+\\\\-×*/÷%()]"))
                        val lastPart = parts.lastOrNull() ?: ""
                        if (lastPart.contains(".")) {
                            return
                        }
                    }

                    _expression.value += symbol
                }
            }
        }
    }
}