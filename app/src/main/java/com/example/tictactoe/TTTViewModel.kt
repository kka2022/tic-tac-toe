package com.example.tictactoe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.data.AiMode
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

        val aiMoveIndex = chooseEmptyRandomIndexForAi(_gameUiState.value.board)

        // check
        val checkedAiMoveIndex = aiMoveSelectHelper(_gameUiState.value.board)
        // check

        val selectedIndex = if (checkedAiMoveIndex == -1) {
            aiMoveIndex
        } else {
            checkedAiMoveIndex
        }

        val newBoard = _gameUiState.value.board.map {
            val updatedSquare = if (it.id == selectedIndex) {
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

    fun chooseEmptyRandomIndexForAi(board: List<Square>): Int {
        var randomSquareIndex = (0..8).random()
        while (board[randomSquareIndex].player != Player.NONE) {
            randomSquareIndex = (0..8).random()
        }
        return randomSquareIndex
    }

    fun aiMoveSelectHelper(board: List<Square>): Int {

        /*
* 0 1 2
* 3 4 5
* 6 7 8
* */

        val listOfIndexTriplets = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6),
        )

        val possibleWinningIndices = getWinningSetOfIndices(board, listOfIndexTriplets)
        Log.d("Indices", "aiMoveSelectHelper: $possibleWinningIndices")
        val chosenIndex = possibleWinningIndices.firstOrNull()?.firstOrNull() { board[it].player != Player.O } ?: -1
        Log.d("CHOSEN", "aiMoveSelectHelper: $chosenIndex")
        return chosenIndex

    }

    fun expertLevelAiMoveSelect(): Int {
        return 0
    }

    fun getWinningSetOfIndices(
        board: List<Square>,
        allSetsOfTrails: List<List<Int>>
    ): List<List<Int>> {
        val possibleWinningTrailsForOpponent = mutableListOf<List<Int>>()
        for (set in allSetsOfTrails) {
            var opponentCount = 0
            var aiCount = 0
            for (index in set) {
                if (board[index].player == Player.O) {
                    opponentCount++
                }
                if (board[index].player == Player.X) {
                    aiCount++
                }
            }
            if (opponentCount == 2 && aiCount == 0) {
                possibleWinningTrailsForOpponent.add(set)
            }
        }
        return possibleWinningTrailsForOpponent
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
                gameMode = GameMode.SinglePlayer,
                aiMode = AiMode.EASY
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
