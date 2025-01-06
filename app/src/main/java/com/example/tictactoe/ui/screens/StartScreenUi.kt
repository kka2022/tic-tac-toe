package com.example.tictactoe.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.tictactoe.R
import com.example.tictactoe.ui.components.DefaultHorizontalSpacer
import com.example.tictactoe.ui.components.StartGameButton

@Composable
fun StartScreenUi(
    onStartSinglePlayerClick: () -> Unit,
    onStartMultiplayerClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.tic_tac_toe_background_image),
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
                onClick = onStartSinglePlayerClick)

            DefaultHorizontalSpacer()
            DefaultHorizontalSpacer()

            StartGameButton(
                text = "Start Multiplayer",
                onClick = onStartMultiplayerClick)

        }
    }
}