package com.example.canvaspizza

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PizzaMenu(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.Red.copy(alpha = 0.1f))
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Pizza(
            pizzaSize = PizzaSize.LARGE,
            cheeseQuantity = CheeseQuantity.DOUBLE_CHEESE,
            vegQuantity = VegQuantity.EXTRA,
            oliveQuantity = OliveQuantity.EXTRA,
            pieces = 8
        )
        Pizza(
            pizzaSize = PizzaSize.MEDIUM,
            cheeseQuantity = CheeseQuantity.REGULAR,
            vegQuantity = VegQuantity.REGULAR,
            oliveQuantity = OliveQuantity.REGULAR,
            pieces = 6
        )
        Pizza(
            pizzaSize = PizzaSize.SMALL,
            cheeseQuantity = CheeseQuantity.CHEESE_BUSTER,
            vegQuantity = VegQuantity.NONE,
            oliveQuantity = OliveQuantity.NONE,
            pieces = 4
        )
    }
}
