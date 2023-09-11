package com.ry05k2ulv.sudokusolver.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ry05k2ulv.sudokusolver.ui.theme.SudokuSolverTheme

sealed class KeyType {
    data class Number(val n: Int) : KeyType()
    object Delete : KeyType()
    object Next : KeyType()
}

/*
1 2 3 <
4 5 6 >
7 8 9 >
 */

@Composable
fun Keypad(
    modifier: Modifier = Modifier,
    onClick: (KeyType) -> Unit = {}
) {
    Row(
        modifier = modifier.padding(4.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 1)
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 4)
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 7)
        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 2)
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 5)
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 8)
        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 3)
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 6)
            NumberButton(modifier = Modifier.weight(1f), onClick = onClick, n = 9)
        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            DeleteButton(modifier = Modifier.weight(1f), onClick = onClick)
            NextButton(modifier = Modifier.weight(2f), onClick = onClick)
        }
    }
}

@Composable
fun NumberButton(modifier: Modifier, onClick: (KeyType) -> Unit, n: Int) {
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp, 4.dp),
        onClick = { onClick(KeyType.Number(n)) },
        border = BorderStroke(2.dp, Color.DarkGray),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(1.dp)
    ) {
        Text(text = n.toString(), fontSize = 24.sp)
    }
}

@Composable
fun DeleteButton(modifier: Modifier, onClick: (KeyType) -> Unit) {
    IconButton(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp, 4.dp),
        onClick = { onClick(KeyType.Delete) }) {
        Icon(
            imageVector = Icons.Filled.Backspace,
            contentDescription = "Delete Key",
            modifier = Modifier
                .fillMaxSize(0.65f)
        )
    }
}

@Composable
fun NextButton(modifier: Modifier, onClick: (KeyType) -> Unit) {
    IconButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xa84444a0)),
        onClick = { onClick(KeyType.Next) },
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "Next Key",
            modifier = Modifier.fillMaxSize(0.5f)
        )
    }
}

@Preview
@Composable
fun KeypadPreview() {
    SudokuSolverTheme {
        Surface {
            Keypad(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
            )
        }
    }
}