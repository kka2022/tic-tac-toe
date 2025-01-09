package com.example.tictactoe.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultHorizontalSpacer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    )
}