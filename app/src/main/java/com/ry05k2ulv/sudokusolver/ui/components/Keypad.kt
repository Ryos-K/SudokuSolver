package com.ry05k2ulv.sudokusolver.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ry05k2ulv.sudokusolver.R.string
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
fun WideKeypad(
    modifier: Modifier = Modifier,
    onClick: (KeyType) -> Unit = {},
    numberColors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    deleteColors: IconButtonColors = IconButtonDefaults.outlinedIconButtonColors(),
    nextColors: IconButtonColors = IconButtonDefaults.filledIconButtonColors(),
    enabled: Boolean = true
) {
    Row(
        modifier = modifier.padding(4.dp, 0.dp), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            NumberButton(Modifier.weight(1f), onClick, 1, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 4, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 7, numberColors, enabled)
        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            NumberButton(Modifier.weight(1f), onClick, 2, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 5, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 8, numberColors, enabled)
        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            NumberButton(Modifier.weight(1f), onClick, 3, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 6, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 9, numberColors, enabled)
        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            DeleteButton(Modifier.weight(1f), onClick, deleteColors, enabled)
            NextButton(Modifier.weight(2f), onClick, nextColors, enabled)
        }
    }
}

@Composable
fun TallKeypad(
    modifier: Modifier = Modifier,
    onClick: (KeyType) -> Unit = {},
    numberColors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    deleteColors: IconButtonColors = IconButtonDefaults.outlinedIconButtonColors(),
    nextColors: IconButtonColors = IconButtonDefaults.filledIconButtonColors(),
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.padding(4.dp, 0.dp), verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            NumberButton(Modifier.weight(1f), onClick, 1, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 2, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 3, numberColors, enabled)
        }
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            NumberButton(Modifier.weight(1f), onClick, 4, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 5, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 6, numberColors, enabled)
        }
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            NumberButton(Modifier.weight(1f), onClick, 7, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 8, numberColors, enabled)
            NumberButton(Modifier.weight(1f), onClick, 9, numberColors, enabled)
        }
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            DeleteButton(Modifier.weight(1f), onClick, deleteColors, enabled)
            NextButton(Modifier.weight(2f), onClick, nextColors, enabled)
        }
    }
}

@Composable
fun NumberButton(
    modifier: Modifier, onClick: (KeyType) -> Unit, n: Int, colors: ButtonColors, enabled: Boolean
) {
    Button(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp, 4.dp),
        onClick = { onClick(KeyType.Number(n)) },
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(1.dp),
        colors = colors,
        enabled = enabled
    ) {
        Text(text = n.toString(), fontSize = 24.sp)
    }
}

@Composable
private fun DeleteButton(
    modifier: Modifier, onClick: (KeyType) -> Unit, colors: IconButtonColors, enabled: Boolean
) {
    IconButton(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp, 4.dp),
        onClick = { onClick(KeyType.Delete) },
        colors = colors,
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Filled.Backspace,
            contentDescription = stringResource(string.keypad_back_icon_description),
            modifier = Modifier.fillMaxSize(0.65f)
        )
    }
}

@Composable
fun NextButton(
    modifier: Modifier, onClick: (KeyType) -> Unit, colors: IconButtonColors, enabled: Boolean
) {
    IconButton(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp, 4.dp),
        onClick = { onClick(KeyType.Next) },
        colors = colors,
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = stringResource(string.keypad_next_icon_description),
            modifier = Modifier.fillMaxSize(0.5f)
        )
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun TallKeypadPreview() {
    SudokuSolverTheme {
        Surface {
            TallKeypad(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(0.75f, true),
                enabled = true
            )
        }
    }
}

@Preview
@Composable
fun WideKeypadPreview() {
    SudokuSolverTheme {
        Surface {
            WideKeypad(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f),
                enabled = true
            )
        }
    }
}