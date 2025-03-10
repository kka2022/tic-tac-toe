package com.example.tictactoe.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartGameButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(red = 189, green = 45, blue = 45, alpha = 255)
        ),
        modifier = Modifier.fillMaxWidth(0.8f).padding(4.dp)
    ) {
        Text(text = text, fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
    }
}