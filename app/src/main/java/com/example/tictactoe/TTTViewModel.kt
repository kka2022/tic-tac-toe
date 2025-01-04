package com.example.tictactoe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.data.GameMode
import com.example.tictactoe.data.GameStatus
import com.example.tictactoe.data.GameUiState
import com.example.tictactoe.data.Player
import com.example.tictactoe.data.Square
import com.example.tictactoe.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TTTViewModel : ViewModel() {
    private val _gameUiState: MutableState<GameUiState> =
        mutableStateOf(GameUiState(whoseTurn = Player.O, gameStatus = GameStatus.YetToBegin))
    val gameUiState = _gameUiState

    fun makeMove(id: Int) {
        val newBoard = _gameUiState.value.board.map {
            val updatedSquare =
                if (
                    _gameUiState.value.gameStatus == GameStatus.On &&
                    it.player == Player.NONE &&
                    it.id == id &&
                    _gameUiState.value.whoseTurn == Player.X
                ) {
                    _gameUiState.value = _gameUiState.value.copy(whoseTurn = Player.O)
                    it.copy(player = Player.X)
                } else if (_gameUiState.value.gameStatus == GameStatus.On &&
                    it.player == Player.NONE &&
                    it.id == id &&
                    _gameUiState.value.whoseTurn == Player.O
                ) {
                    _gameUiState.value = _gameUiState.value.copy(whoseTurn = Player.X)
                    it.copy(player = Player.O)
                } else {
                    it
                }
            updatedSquare
        }

        val newGameStatus = Utils.checkWinner(newBoard)

        _gameUiState.value = _gameUiState.value.copy(
            board = newBoard,
            gameStatus = newGameStatus
        )

        if (_gameUiState.value.gameMode == GameMode.SinglePlayer && _gameUiState.value.whoseTurn == Player.X) {
            viewModelScope.launch(Dispatchers.Default) {
                delay(200L)

                if (_gameUiState.value.gameStatus == GameStatus.On) {
                    makeAiMove()
                }
            }
        }
    }

    fun makeAiMove() {

        val aiMoveIndex = aiMoveSelectHelper(_gameUiState.value.board)

        val newBoard = _gameUiState.value.board.map {
            val updatedSquare = if (it.id == aiMoveIndex) {
                it.copy(player = Player.X)
            } else {
                it
            }
            updatedSquare
        }

        val newGameStatus = Utils.checkWinner(newBoard)

        _gameUiState.value = _gameUiState.value.copy(
            board = newBoard,
            gameStatus = newGameStatus,
            whoseTurn = Player.O
        )
    }

    fun aiMoveSelectHelper(board: List<Square>) : Int {
        var randomSquareIndex = (0..8).random()
        while (board[randomSquareIndex].player != Player.NONE) {
            randomSquareIndex = (0..8).random()
        }

        val listOfIndexTriplets = listOf(setOf(0,1,2))

        return randomSquareIndex
    }

    fun startMultiplayerGame() {
        _gameUiState.value =
            _gameUiState.value.copy(
                board = List(9) { Square(id = it, player = Player.NONE) },
                whoseTurn = Player.O,
                gameStatus = GameStatus.On,
                gameMode = GameMode.MultiPlayer
            )
    }

    fun startSinglePlayerGame() {
        _gameUiState.value =
            _gameUiState.value.copy(
                board = List(9) { Square(id = it, player = Player.NONE) },
                whoseTurn = Player.O,
                gameStatus = GameStatus.On,
                gameMode = GameMode.SinglePlayer
            )
    }

    fun restartGame() {
        _gameUiState.value =
            _gameUiState.value.copy(
                board = List(9) { Square(id = it, player = Player.NONE) },
                whoseTurn = Player.O,
                gameStatus = GameStatus.On,
                gameMode = if (_gameUiState.value.gameMode == GameMode.SinglePlayer) GameMode.SinglePlayer else GameMode.MultiPlayer
            )
    }
}
