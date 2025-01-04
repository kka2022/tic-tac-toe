package com.example.tictactoe.ui.components

import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.data.Player
import com.example.tictactoe.data.Square

@Composable
fun TTTSquare(square: Square, onSquareClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .border(width = 1.dp, color = Color.Black)
            .clickable { onSquareClick(square.id) }
        ,
        contentAlignment = Alignment.Center
    ) {
//        val boardMove = if (square.player == Player.NONE) "" else square.player.toString()
//        Text(text = boardMove, fontSize = 20.sp)

        AnimatedContent(targetState = square.player, label = "") {
            when (it) {
                Player.X -> {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val canvasWidth = size.width
                        val canvasHeight = size.height
                        drawLine(
                            start = Offset(x = 0.2f * canvasWidth, y = 0.2f * canvasHeight),
                            end = Offset(
                                x = canvasWidth - 0.2f * canvasWidth,
                                y = canvasHeight - 0.2f * canvasHeight
                            ),
                            strokeWidth = Stroke.DefaultMiter,
                            color = Color.Blue
                        )
                        drawLine(
                            start = Offset(
                                x = canvasWidth - 0.2f * canvasWidth,
                                y = 0.2f * canvasHeight
                            ),
                            end = Offset(
                                x = 0.2f * canvasWidth,
                                y = canvasHeight - 0.2f * canvasHeight
                            ),
                            strokeWidth = Stroke.DefaultMiter,
                            color = Color.Blue
                        )
                    }
                }

                Player.O -> {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = Color.Blue,
                            radius = size.minDimension / 2.0f - 0.2f * size.height,
                            style = Stroke(width = 4f)
                        )
                    }
                }

                else -> {
                    // TODO
                }
            }
        }
    }
}