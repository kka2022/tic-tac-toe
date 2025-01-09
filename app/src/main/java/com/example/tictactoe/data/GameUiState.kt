package com.example.tictactoe.data

data class GameUiState(
    val board: List<Square> = List(9) { Square(id = it, player = Player.NONE) },
    val whoseTurn: Player,
    val gameStatus: GameStatus,
    val gameMode: GameMode = GameMode.MultiPlayer,
    val aiMode: AiMode
)