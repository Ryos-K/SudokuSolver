package com.ry05k2ulv.sudokusolver.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ry05k2ulv.sudokusolver.solver.MutableStateSudokuTable
import com.ry05k2ulv.sudokusolver.solver.Position
import com.ry05k2ulv.sudokusolver.solver.SudokuTable
import com.ry05k2ulv.sudokusolver.ui.theme.SudokuSolverTheme


@OptIn(ExperimentalTextApi::class)
@Composable
fun Sudoku(
    modifier: Modifier = Modifier,
    table: SudokuTable = SudokuTable(),
    selected: Position = Position(0, 0),
    onSelected: (Position) -> Unit = {},
    showHighlight: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    lineColor: Color = MaterialTheme.colorScheme.onBackground,
    highlightColor: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
    contentColor: Color = MaterialTheme.colorScheme.onBackground
) {
    val textMeasurer = rememberTextMeasurer()
    val animatedSelected by
    animateOffsetAsState(
        targetValue = Offset(
            selected.x.toFloat(),
            selected.y.toFloat()
        ),
        label = "animated selected position",
        animationSpec = tween(
            durationMillis = 200,
            delayMillis = 50,
            easing = LinearOutSlowInEasing
        )
    )



    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier
            .aspectRatio(1f)
            .background(backgroundColor)
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
            if (showHighlight) highlightCell(animatedSelected, highlightColor)
            drawFrame(lineColor)
            drawNumber(textMeasurer, table, contentColor)
        })
    }
}

fun DrawScope.drawFrame(
    color: Color
) {
    val cellWidth = size.width / 9
    val cellHeight = size.height / 9
    val boldLines = listOf(3, 6)
    val thinLines = listOf(1, 2, 4, 5, 7, 8)

    // draw outline frame
    drawRoundRect(
        color = color,
        size = size,
        cornerRadius = CornerRadius(16f, 16f),
        style = Stroke(width = 3.dp.toPx()),
    )

    // draw bold lines
    boldLines.forEach {
        drawLine(
            color = color,
            start = Offset(it * cellWidth, 0f),
            end = Offset(it * cellWidth, size.height),
            strokeWidth = 3.dp.toPx()
        ) // vertical lines
        drawLine(
            color = color,
            start = Offset(0f, it * cellHeight),
            end = Offset(size.height, it * cellHeight),
            strokeWidth = 3.dp.toPx()
        ) // horizontal lines
    }
    // draw thin lines
    thinLines.forEach {
        drawLine(
            color = color,
            start = Offset(it * cellWidth, 0f),
            end = Offset(it * cellWidth, size.height),
            strokeWidth = 1.dp.toPx()
        ) // vertical lines
        drawLine(
            color = color,
            start = Offset(0f, it * cellHeight),
            end = Offset(size.width, it * cellHeight),
            strokeWidth = 1.dp.toPx()
        ) // horizontal lines
    }
}

fun DrawScope.highlightCell(pos: Offset, color: Color) {
    val cellWidth = size.width / 9
    val cellHeight = size.height / 9

    drawRoundRect(
        color = color,
        topLeft = Offset(pos.x * cellWidth, pos.y * cellHeight),
        size = Size(cellWidth, cellHeight),
        cornerRadius = CornerRadius(24f, 24f)
    )
}

@OptIn(ExperimentalTextApi::class)
fun DrawScope.drawNumber(textMeasurer: TextMeasurer, table: SudokuTable, color: Color) {
    val cellWidth = size.width / 9
    val cellHeight = size.height / 9

    for (ci in 0..8) {
        for (ri in 0..8) {
            drawText(
                textMeasurer,
                table[ci, ri].toString(),
                topLeft = Offset((ci + 0.25f) * cellWidth, ri * cellHeight),
                style = TextStyle(fontSize = (cellWidth * 0.8f).toSp(), color = color)
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
                    table = MutableStateSudokuTable(),
                    selected = selected,
                    onSelected = { selected = it }
                )
            }
        }
    }
}