package com.ry05k2ulv.sudokusolver.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ry05k2ulv.sudokusolver.ui.theme.SudokuSolverTheme

data class Coordinate(val x: Int, val y: Int)


@Composable
fun Sudoku(
    modifier: Modifier,
    plate: Array<Array<Int?>> = Array(9) { Array(9) { null } },
    selected: Coordinate = Coordinate(0, 0),
    onSelected: (Coordinate) -> Unit = {},
) {

    Canvas(modifier = modifier, onDraw = {
        drawFrame()
    })
}

fun DrawScope.drawFrame() {
    val cellWidth = size.width / 9
    val cellHeight = size.height / 9
    val boldLines = listOf(0, 3, 6, 9)
    val thinLines = listOf(1, 2, 4, 5, 7, 8)

    // draw bold lines
    boldLines.forEach {
        drawLine(
            color = Color.Black,
            start = Offset(it * cellWidth, 0f),
            end = Offset(it * cellWidth, size.height),
            strokeWidth = 3.dp.toPx()
        ) // vertical lines
        drawLine(
            color = Color.Black,
            start = Offset(0f, it * cellHeight),
            end = Offset(size.height, it * cellHeight),
            strokeWidth = 3.dp.toPx()
        ) // horizontal lines
    }
    // draw thin lines
    thinLines.forEach {
        drawLine(
            color = Color.DarkGray,
            start = Offset(it * cellWidth, 0f),
            end = Offset(it * cellWidth, size.height),
            strokeWidth = 1.dp.toPx()
        ) // vertical lines
        drawLine(
            color = Color.DarkGray,
            start = Offset(0f, it * cellHeight),
            end = Offset(size.width, it * cellHeight),
            strokeWidth = 1.dp.toPx()
        ) // horizontal lines
    }
}

@Preview
@Composable
fun SudokuPreview() {
    SudokuSolverTheme {
        Surface(
            modifier = Modifier.padding(8.dp),
        ) {
            Sudoku(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
    }
}