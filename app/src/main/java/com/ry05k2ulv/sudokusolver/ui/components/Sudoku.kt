package com.ry05k2ulv.sudokusolver.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ry05k2ulv.sudokusolver.ui.theme.SudokuSolverTheme

data class Position(val x: Int, val y: Int) {
    fun next() = when {
        x == 8 && y == 8 -> null
        x == 8 -> Position(0, y + 1)
        else -> Position(x + 1, y)
    }

    fun prev() = when {
        x == 0 && y == 0 -> null
        x == 0 -> Position(8, y - 1)
        else -> Position(x - 1, y)
    }
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun Sudoku(
    modifier: Modifier = Modifier,
    table: Array<Array<Int?>> = Array(9) { Array(9) { null } },
    selected: Position = Position(0, 0),
    onSelected: (Position) -> Unit = {},
) {
    val textMeasurer = rememberTextMeasurer()
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier
            .aspectRatio(1f)
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { tapOffset ->
                        val cellWidth = size.width / 9
                        val cellHeight = size.height / 9

                        val tapPosition = Position(
                            (tapOffset.x / cellWidth).let {
                                when {
                                    it < 0f -> 0
                                    it >= 9f -> 8
                                    else -> it.toInt()
                                }
                            },
                            (tapOffset.y / cellHeight).let {
                                when {
                                    it < 0f -> 0
                                    it >= 9f -> 8
                                    else -> it.toInt()
                                }
                            },
                        )
                        onSelected(tapPosition)
                    }
                )
            }, onDraw = {
            highlightCell(selected, Color(0x80c04040))
            drawFrame()
            drawNumber(textMeasurer, table)
        })
    }
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

fun DrawScope.highlightCell(pos: Position, color: Color) {
    val cellWidth = size.width / 9
    val cellHeight = size.height / 9

    drawRect(
        color = color,
        topLeft = Offset(pos.x * cellWidth, pos.y * cellHeight),
        size = Size(cellWidth, cellHeight)
    )
}

@OptIn(ExperimentalTextApi::class)
fun DrawScope.drawNumber(textMeasurer: TextMeasurer, table: Array<Array<Int?>>) {
    val cellWidth = size.width / 9
    val cellHeight = size.height / 9

    table.forEachIndexed { ci, col ->
        col.forEachIndexed { ri, cell ->
            drawText(
                textMeasurer,
                cell?.toString() ?: "",
                topLeft = Offset((ci + 0.25f) * cellWidth, ri * cellHeight),
                style = TextStyle(fontSize = (cellWidth * 0.8f).toSp())
            )
        }
    }
}

@Preview
@Composable
fun SudokuPreview() {
    SudokuSolverTheme {
        Surface {
            Box(modifier = Modifier.padding(8.dp)) {
                var selected by remember { mutableStateOf(Position(0, 0)) }
                Sudoku(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    table = Array(9) { x -> Array(9) { y -> (x + y) % 9 + 1 } },
                    selected = selected,
                    onSelected = { selected = it }
                )
            }
        }
    }
}