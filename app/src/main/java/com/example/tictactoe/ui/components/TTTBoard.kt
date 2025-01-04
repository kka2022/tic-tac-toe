package com.example.tictactoe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.tictactoe.data.Square

@Composable
fun TTTBoard(board: List<Square>, onSquareClick: (Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0..2) {
            Row {
                for (j in 0..2) {
                    Column {
                        val square = board[j + i * 3]
                        TTTSquare(
                            square = square,
                            onSquareClick = onSquareClick
                        )
                    }
                }
            }
        }
    }
}
