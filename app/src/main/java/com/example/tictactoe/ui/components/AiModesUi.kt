package com.example.tictactoe.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.tictactoe.data.AiMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiModesUi(modifier: Modifier = Modifier) {
    Column {
        Text("Ai Mode")

        var selectedIndex by remember { mutableIntStateOf(0) }
        val options = listOf(AiMode.EASY, AiMode.HARD, AiMode.EXPERT)

        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    ),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex,
                    label = { Text(label.toString()) }
                )
            }
        }
    }
}