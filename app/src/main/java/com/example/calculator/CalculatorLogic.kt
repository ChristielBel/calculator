package com.example.calculator

import java.util.Stack

fun evaluateExpression(expression: String): String {
    return try {
        val tokens = tokenize(expression)
        val rpn = toRPN(tokens)
        val result = evalRPN(rpn)
        result.toString()
    } catch (e: Exception) {
        "Error"
    }
}

private fun tokenize(expr: String): List<String> {
    val tokens = mutableListOf<String>()
    var number = ""
    for (ch in expr) {
        when {
            ch.isDigit() || ch == '.' -> number += ch

            ch in listOf('+', '−', '-', '×', '*', '÷', '/') -> {
                if (number.isNotEmpty()) {
                    tokens.add(number)
                    number = ""
                }
                tokens.add(ch.toString())
            }

            ch == '(' || ch == ')' -> {
                if (number.isNotEmpty()) {
                    tokens.add(number)
                    number = ""
                }
                tokens.add(ch.toString())
            }
        }
    }
    if (number.isNotEmpty()) tokens.add(number)
    return tokens
}

private fun toRPN(tokens: List<String>): List<String> {
    val output = mutableListOf<String>()
    val operators = Stack<String>()
    val precedence = mapOf(
        "+" to 1, "-" to 1,
        "×" to 2, "*" to 2,
        "÷" to 2, "/" to 2
    )

    for (token in tokens) {
        when {
            token.toDoubleOrNull() != null -> output.add(token)
            token in precedence.keys -> {
                while (operators.isNotEmpty() && operators.peek() != "(" &&
                    precedence[operators.peek()]!! >= precedence[token]!!
                ) {
                    output.add(operators.pop())
                }
                operators.push(token)
            }

            token == "(" -> operators.push(token)
            token == ")" -> {
                while (operators.isNotEmpty() && operators.peek() != "(") {
                    output.add(operators.pop())
                }
                if (operators.isNotEmpty() && operators.peek() == "(") {
                    operators.pop()
                }
            }
        }
    }
    while (operators.isNotEmpty()) {
        output.add(operators.pop())
    }
    return output
}

private fun evalRPN(tokens: List<String>): Double {
    val stack = Stack<Double>()
    for (token in tokens) {
        when {
            token.toDoubleOrNull() != null -> stack.push(token.toDouble())
            else -> {
                val b = stack.pop()
                val a = stack.pop()
                val result = when (token) {
                    "+" -> a + b
                    "-" -> a - b
                    "×", "*" -> a * b
                    "÷", "/" -> a / b
                    else -> throw IllegalArgumentException("Unknown operator: $token")
                }
                stack.push(result)
            }
        }
    }
    return stack.pop()
}