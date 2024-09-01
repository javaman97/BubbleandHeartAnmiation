package com.aman.bubbleanmiation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.graphics.Color


// Data class to represent each bubble

data class Bubble(
    val color: Color,
    val size: Float,
    val x: Animatable<Float, AnimationVector1D>,
    val y: Animatable<Float, AnimationVector1D>,
    var dx: Float, // Velocity in the x direction
    var dy: Float  // Velocity in the y direction
) {
    // Function to update the bubble's position
    suspend fun updatePosition() {
        x.snapTo(x.value + dx)
        y.snapTo(y.value + dy)
    }

    // Function to make the bubble bounce off the edges
    fun bounceOffEdges(width: Float, height: Float) {
        if (x.value <= 0 || x.value >= width) {
            dx = -dx // Reverse direction on X-axis
        }
        if (y.value <= 0 || y.value >= height) {
            dy = -dy // Reverse direction on Y-axis
        }
    }
}



// Updated Heart class with dx and dy
data class Heart(
    val color: Color,
    val size: Float,
    val x: Animatable<Float, AnimationVector1D>,
    val y: Animatable<Float, AnimationVector1D>,
    var dx: Float, // Velocity in the x direction
    var dy: Float  // Velocity in the y direction
) {
    // Function to update the heart's position
    suspend fun updatePosition() {
        x.snapTo(x.value + dx)
        y.snapTo(y.value + dy)
    }

    // Function to make the heart bounce off the edges
    fun bounceOffEdges(width: Float, height: Float) {
        if (x.value <= 0 || x.value >= width) {
            dx = -dx // Reverse direction on X-axis
        }
        if (y.value <= 0 || y.value >= height) {
            dy = -dy // Reverse direction on Y-axis
        }
    }
}