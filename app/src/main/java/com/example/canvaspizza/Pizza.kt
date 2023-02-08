package com.example.canvaspizza

import android.graphics.ComposePathEffect
import android.graphics.CornerPathEffect
import android.graphics.DiscretePathEffect
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.canvaspizza.ui.theme.*
import kotlin.random.Random

@Composable
fun Pizza(
    modifier: Modifier = Modifier,
    pizzaSize: PizzaSize = PizzaSize.MEDIUM,
    cheeseQuantity: CheeseQuantity = CheeseQuantity.NONE,
    vegQuantity: VegQuantity = VegQuantity.NONE,
    oliveQuantity: OliveQuantity = OliveQuantity.NONE,
    pieces: Int = 8
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .size(300.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .background(Color.White)
    ) {
        Canvas(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Crust
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        CrustColor.copy(alpha = 0f),
                        CrustColor
                    )
                ),
                radius = pizzaSize.radiusFraction * size.minDimension / 2f
            )

            // Sauce
            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    center.x,
                    center.y,
                    (pizzaSize.radiusFraction * size.minDimension / 2f) - 8.dp.toPx(),
                    Paint().apply {
                        this.color = SauceColor.hashCode()
                        pathEffect = ComposePathEffect(
                            // Gives roundness to the segments
                            CornerPathEffect(100f),
                            // Breaks Circle into multiple segments of 60px, and deviates it by 15px
                            DiscretePathEffect(60f, 15f),
                        )
                    }
                )
            }

            // Cheese
            for (cheeseParticle in 1..cheeseQuantity.particleCount) {
                val angle = Random.nextInt(360).toFloat()
                val positionX = Random.nextInt(0, size.width.toInt() / 2).toFloat()
                val positionY = Random.nextInt(0, size.height.toInt() / 2).toFloat()
                clipPath(
                    path = Path().apply {
                        addOval(
                            Rect(
                                center = center,
                                radius = pizzaSize.radiusFraction * size.minDimension / 2f - 16.dp.toPx()
                            )
                        )
                    }
                ) {
                    drawArc(
                        color = CheeseColor,
                        startAngle = angle,
                        sweepAngle = Random.nextInt(3, 10).toFloat(),
                        useCenter = false,
                        style = Stroke(Random.nextInt(4, 8).toFloat()),
                        topLeft = Offset(x = positionX, y = positionY),
                        size = Size(width = size.width / 2, height = size.height / 2)
                    )
                }
            }

            // Veg
            for (veg in 1..vegQuantity.particleCount) {
                val angle = Random.nextInt(360).toFloat()
                val positionX = Random.nextInt(0, size.width.toInt() / 2).toFloat()
                val positionY = Random.nextInt(0, size.height.toInt() / 2).toFloat()
                clipPath(
                    path = Path().apply {
                        addOval(
                            Rect(
                                center = center,
                                radius = pizzaSize.radiusFraction * size.minDimension / 2f - 24.dp.toPx()
                            )
                        )
                    }
                ) {
                    drawArc(
                        color = VegColor,
                        startAngle = angle,
                        sweepAngle = Random.nextInt(10, 20).toFloat(),
                        useCenter = false,
                        style = Stroke(Random.nextInt(12, 16).toFloat()),
                        topLeft = Offset(x = positionX, y = positionY),
                        size = Size(width = size.width / 2, height = size.height / 2)
                    )
                }
            }

            // Olive
            for (olive in 1..oliveQuantity.particleCount) {
                val positionX = Random.nextInt(0, (pizzaSize.radiusFraction * size.width / 2).toInt()).toFloat()
                val positionY = Random.nextInt(0, (pizzaSize.radiusFraction * size.height / 2).toInt()).toFloat()
                drawCircle(
                    color = OliveColor,
                    radius = 6.dp.toPx(),
                    center = Offset(x = center.x / 2 + positionX, y = center.y / 2 + positionY),
                    style = Stroke(Random.nextInt(12, 16).toFloat())
                )
            }

            // Pieces
            for (angle in 0..360 step 360 / pieces) {
                rotate(angle.toFloat()) {
                    drawRect(
                        color = Color.White,
                        topLeft = center,
                        size = Size(pizzaSize.radiusFraction * center.x, 3.dp.toPx())
                    )
                }
            }

        }
    }
}
