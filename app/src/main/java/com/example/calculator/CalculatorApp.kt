package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.interfaces.ICalculatorViewModel
import com.example.calculator.ui.theme.*
import com.example.calculator.viewmodel.FakeCalculatorViewModel

@Composable
fun CalculatorApp(viewModel: ICalculatorViewModel) {
    val expression = viewModel.expression.collectAsState()
    val result = viewModel.result.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .padding(CalculatorPadding),
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = result.value,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                maxLines = 1,
                fontSize = CalculatorResultFontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = expression.value,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                maxLines = 1,
                fontSize = CalculatorExpressionFontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val buttons = listOf(
            listOf("C", "%", "⌫", "÷"),
            listOf("7", "8", "9", "×"),
            listOf("4", "5", "6", "-"),
            listOf("1", "2", "3", "+"),
            listOf("00", "0", ",", "="),
        )

        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(CalculatorButtonSpacing)
        ) {
            for (row in buttons) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(CalculatorButtonSpacing)
                ) {
                    for (symbol in row) {
                        Button(
                            onClick = { viewModel.onButtonClick(symbol) },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (symbol in listOf(
                                        "+",
                                        "−",
                                        "-",
                                        "×",
                                        "*",
                                        "÷",
                                        "/",
                                        "C",
                                        "%",
                                        "⌫",
                                    )
                                )
                                    OperatorButtonColor else ButtonColor,
                                contentColor = ButtonTextColor
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                        ) {
                            Text(
                                text = symbol,
                                fontSize = CalculatorButtonFontSize
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorApp() {
    CalculatorTheme {
        CalculatorApp(viewModel = FakeCalculatorViewModel())
    }
}