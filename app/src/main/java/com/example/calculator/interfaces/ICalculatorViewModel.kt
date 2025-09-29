package com.example.calculator.interfaces

import kotlinx.coroutines.flow.StateFlow

interface ICalculatorViewModel {
    val expression: StateFlow<String>
    val result: StateFlow<String>
    fun onButtonClick(symbol: String)
}