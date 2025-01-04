package com.example.tictactoe.utils

import com.example.tictactoe.data.GameStatus
import com.example.tictactoe.data.Player
import com.example.tictactoe.data.Square

object Utils {
    fun checkWinner(board: List<Square>): GameStatus {
        return when {
            (board[0].player == Player.X && board[1].player == Player.X && board[2].player == Player.X) ||
                    (board[3].player == Player.X && board[4].player == Player.X && board[5].player == Player.X) ||
                    (board[6].player == Player.X && board[7].player == Player.X && board[8].player == Player.X) ||
                    (board[0].player == Player.X && board[3].player == Player.X && board[6].player == Player.X) ||
                    (board[1].player == Player.X && board[4].player == Player.X && board[7].player == Player.X) ||
                    (board[2].player == Player.X && board[5].player == Player.X && board[8].player == Player.X) ||
                    (board[0].player == Player.X && board[4].player == Player.X && board[8].player == Player.X) ||
                    (board[2].player == Player.X && board[4].player == Player.X && board[6].player == Player.X) -> {
                GameStatus.XWinner
            }

            (board[0].player == Player.O && board[1].player == Player.O && board[2].player == Player.O) ||
                    (board[3].player == Player.O && board[4].player == Player.O && board[5].player == Player.O) ||
                    (board[6].player == Player.O && board[7].player == Player.O && board[8].player == Player.O) ||
                    (board[0].player == Player.O && board[3].player == Player.O && board[6].player == Player.O) ||
                    (board[1].player == Player.O && board[4].player == Player.O && board[7].player == Player.O) ||
                    (board[2].player == Player.O && board[5].player == Player.O && board[8].player == Player.O) ||
                    (board[0].player == Player.O && board[4].player == Player.O && board[8].player == Player.O) ||
                    (board[2].player == Player.O && board[4].player == Player.O && board[6].player == Player.O) -> {
                GameStatus.OWinner
            }

            (board[0].player != Player.NONE && board[1].player != Player.NONE && board[2].player != Player.NONE && board[3].player != Player.NONE && board[4].player != Player.NONE && board[5].player != Player.NONE && board[6].player != Player.NONE && board[7].player != Player.NONE && board[8].player != Player.NONE) -> {
                GameStatus.Draw
            }

            else -> {
                GameStatus.On
            }
        }
    }
}