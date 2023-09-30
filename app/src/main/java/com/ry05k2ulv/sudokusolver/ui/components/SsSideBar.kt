package com.ry05k2ulv.sudokusolver.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ry05k2ulv.sudokusolver.ui.theme.SudokuSolverTheme

@Composable
fun SsSideBar(
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.primary,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(56.dp)
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Preview
@Composable
fun SsSideBarPreview() {
    SudokuSolverTheme {
        Surface {
            SsSideBar() {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "refresh")
                }
            }
        }
    }
}