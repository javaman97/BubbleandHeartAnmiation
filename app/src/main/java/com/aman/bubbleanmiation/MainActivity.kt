package com.aman.bubbleanmiation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import com.aman.bubbleanmiation.ui.theme.BubbleAnmiationTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubbleAnmiationTheme {
                BubbleAnimation()
                HeartAnimation()
            }
        }
    }
}

@Composable
fun BubbleAnimation() {

    val bubbleCount = 50
    val bubbleMaxSize = 80f


    val bubbles = remember { generateBubbles(bubbleCount, bubbleMaxSize) }

    LaunchedEffect(Unit) {
        while (true) {
            for (bubble in bubbles) {

                bubble.updatePosition()
            }
            delay(16L)
        }
    }

    // Drawing the bubbles on Canvas
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        bubbles.forEach { bubble ->
            // Bounce off edges using dynamic width and height
            bubble.bounceOffEdges(canvasWidth, canvasHeight)

            drawCircle(
                color = bubble.color,
                radius = bubble.size,
                center = Offset(bubble.x.value, bubble.y.value)
            )
        }
    }
}

// Updated generateBubbles function to include dx and dy
fun generateBubbles(count: Int, maxSize: Float): List<Bubble> {
    return List(count) {
        val color = Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat(),
            alpha = 0.6f
        )
        val size = Random.nextFloat() * maxSize

        Bubble(
            color = color,
            size = size,
            x = Animatable(Random.nextFloat() * 1000), // Start position (adjust bounds)
            y = Animatable(Random.nextFloat() * 1000), // Start position (adjust bounds)
            dx = Random.nextFloat() * 6 - 3, // Random velocity in x (-3 to 3)
            dy = Random.nextFloat() * 6 - 3
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BubbleAnimationPreview() {
    MaterialTheme {
        BubbleAnimation()
    }
}




@Composable
fun HeartAnimation() {
    // Parameters for the hearts
    val heartCount = 50
    val heartMaxSize = 80f

    // List to hold heart states
    val hearts = remember { generateHearts(heartCount, heartMaxSize) }

    LaunchedEffect(Unit) {
        while (true) {
            for (heart in hearts) {
                // Update position
                heart.updatePosition()
            }
            delay(16L) // ~60 FPS
        }
    }

    // Drawing the hearts on Canvas
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        hearts.forEach { heart ->
            // Bounce off edges using dynamic width and height
            heart.bounceOffEdges(canvasWidth, canvasHeight)

            drawHeart(
                color = heart.color,
                center = Offset(heart.x.value, heart.y.value),
                size = heart.size
            )
        }
    }
}


// Updated generateHearts function to include dx and dy and randomize initial positions
fun generateHearts(count: Int, maxSize: Float): List<Heart> {
    return List(count) {
        val color = Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat(),
            alpha = 0.6f
        )
        val size = Random.nextFloat() * maxSize

        Heart(
            color = color,
            size = size,
            x = Animatable(Random.nextFloat() * 1000), // Start position (adjust bounds)
            y = Animatable(Random.nextFloat() * 1000), // Start position (adjust bounds)
            dx = Random.nextFloat() * 6 - 3, // Random velocity in x (-3 to 3)
            dy = Random.nextFloat() * 6 - 3  // Random velocity in y (-3 to 3)
        )
    }
}

// Function to draw a heart shape using Bézier curves
fun DrawScope.drawHeart(color: Color, center: Offset, size: Float) {
    val path = androidx.compose.ui.graphics.Path().apply {
        moveTo(center.x, center.y - size / 4)

        // Left side of the heart
        cubicTo(
            center.x - size, center.y - size,  // Control point 1
            center.x - size, center.y + size / 2,  // Control point 2
            center.x, center.y + size // End point
        )

        // Right side of the heart
        cubicTo(
            center.x + size, center.y + size / 2,  // Control point 1
            center.x + size, center.y - size,  // Control point 2
            center.x, center.y - size / 4 // End point
        )
        close()
    }

    drawPath(
        path = path,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun HeartAnimationPreview() {
    MaterialTheme {
        HeartAnimation()
    }
}
