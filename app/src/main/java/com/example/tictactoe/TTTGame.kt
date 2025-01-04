package com.example.tictactoe

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.data.GameStatus
import com.example.tictactoe.ui.components.DefaultHorizontalSpacer
import com.example.tictactoe.ui.components.GameResult
import com.example.tictactoe.ui.components.RestartGame
import com.example.tictactoe.ui.components.StartGameButton
import com.example.tictactoe.ui.components.TTTBoard
import com.example.tictactoe.ui.components.WhoseTurn

@Composable
fun TicTacToeGame(gameViewModel: TTTViewModel) {
    val gameUiState = gameViewModel.gameUiState.value

    AnimatedContent(
        targetState = gameUiState.gameStatus, label = ""
    ) {
        when (it) {
            GameStatus.YetToBegin -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.blackboard_tic_tac_toe),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillHeight
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        repeat(4) { DefaultHorizontalSpacer() }

                        StartGameButton(
                            text = "Start Single Player",
                            onClick = { gameViewModel.startSinglePlayerGame() })

                        DefaultHorizontalSpacer()
                        DefaultHorizontalSpacer()

                        StartGameButton(
                            text = "Start Multiplayer",
                            onClick = { gameViewModel.startMultiplayerGame() })

                    }
                }
            }

            else -> {
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


                            WhoseTurn(gameUiState.whoseTurn)

                            DefaultHorizontalSpacer()
                            DefaultHorizontalSpacer()

                            TTTBoard(
                                board = gameUiState.board,
                                onSquareClick = {
                                    gameViewModel.makeMove(it)
                                }
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
                                    onClick = { gameViewModel.startSinglePlayerGame() })

                                DefaultHorizontalSpacer()
                                DefaultHorizontalSpacer()

                                StartGameButton(
                                    text = "Start Multiplayer",
                                    onClick = { gameViewModel.startMultiplayerGame() })
                            } else {
                                StartGameButton(text = "Restart Game") { gameViewModel.restartGame() }
                            }
                        }
                    }
                }
            }
        }
    }
}