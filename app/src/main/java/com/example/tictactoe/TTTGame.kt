package com.example.tictactoe

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.tictactoe.data.GameStatus
import com.example.tictactoe.ui.screens.GameMainScreenUi
import com.example.tictactoe.ui.screens.StartScreenUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeGame(gameViewModel: TTTViewModel) {
    val gameUiState = gameViewModel.gameUiState.value

    AnimatedContent(
        targetState = gameUiState.gameStatus, label = ""
    ) {
        when (it) {
            GameStatus.YetToBegin -> {
                StartScreenUi(
                    onStartSinglePlayerClick = { gameViewModel.startSinglePlayerGame() },
                    onStartMultiplayerClick = { gameViewModel.startMultiplayerGame() }
                )
            }

            else -> {
                GameMainScreenUi(
                    gameUiState = gameViewModel.gameUiState.value,
                    onSquareClick = { gameViewModel.makeMove(it) },
                    onStartSinglePlayerGameClick = { gameViewModel.startSinglePlayerGame() },
                    onStartMultiplayerGameClick = { gameViewModel.startMultiplayerGame() },
                    onRestartGameButtonClick = { gameViewModel.restartGame() }
                )
            }
        }
    }
}