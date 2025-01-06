package com.example.tictactoe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tictactoe.data.AiMode
import com.example.tictactoe.data.GameStatus
import com.example.tictactoe.data.GameUiState
import com.example.tictactoe.ui.components.AiModesUi
import com.example.tictactoe.ui.components.DefaultHorizontalSpacer
import com.example.tictactoe.ui.components.GameResult
import com.example.tictactoe.ui.components.StartGameButton
import com.example.tictactoe.ui.components.TTTBoard
import com.example.tictactoe.ui.components.WhoseTurn

@Composable
fun GameMainScreenUi(
    gameUiState: GameUiState,
    onStartSinglePlayerGameClick: () -> Unit,
    onStartMultiplayerGameClick: () -> Unit,
    onRestartGameButtonClick: () -> Unit,
    onSquareClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
//                    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                if (gameUiState.aiMode != AiMode.NONE) {
                    // TODO: Implement Logic
                    AiModesUi()
                }

                DefaultHorizontalSpacer()
                DefaultHorizontalSpacer()

                WhoseTurn(gameUiState.whoseTurn)

                DefaultHorizontalSpacer()
                DefaultHorizontalSpacer()

                TTTBoard(
                    board = gameUiState.board,
                    onSquareClick = onSquareClick
                )

                DefaultHorizontalSpacer()
                DefaultHorizontalSpacer()
                DefaultHorizontalSpacer()
                DefaultHorizontalSpacer()

                GameResult(gameUiState.gameStatus.name)

                DefaultHorizontalSpacer()
                DefaultHorizontalSpacer()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {


                if (gameUiState.gameStatus == GameStatus.Draw ||
                    gameUiState.gameStatus == GameStatus.XWinner ||
                    gameUiState.gameStatus == GameStatus.OWinner
                ) {
                    StartGameButton(
                        text = "Start Single Player",
                        onClick = onStartSinglePlayerGameClick)

                    DefaultHorizontalSpacer()
                    DefaultHorizontalSpacer()

                    StartGameButton(
                        text = "Start Multiplayer",
                        onClick = onStartMultiplayerGameClick)
                } else {
                    StartGameButton(text = "Restart Game", onClick = onRestartGameButtonClick)
                }
            }
        }
    }
}