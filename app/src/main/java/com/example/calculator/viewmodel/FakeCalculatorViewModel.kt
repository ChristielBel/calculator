package com.example.calculator.viewmodel

import com.example.calculator.interfaces.ICalculatorViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeCalculatorViewModel : ICalculatorViewModel {
    private val _expression = MutableStateFlow("12+34")
    override val expression: StateFlow<String> get() = _expression

    private val _result = MutableStateFlow("46")
    override val result: StateFlow<String> get() = _result

    override fun onButtonClick(symbol: String) {
    }
}