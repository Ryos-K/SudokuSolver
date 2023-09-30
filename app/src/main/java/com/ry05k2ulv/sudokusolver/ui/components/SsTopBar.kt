package com.ry05k2ulv.sudokusolver.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun SsTopBar(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = MaterialTheme.colorScheme.onPrimary,
    background: Color = MaterialTheme.colorScheme.primary,
    content: @Composable() (RowScope.() -> Unit)
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .background(background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(16.dp, 8.dp)
                .weight(1f),
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            color = titleColor
        )
        content()
    }
}

@Preview
@Composable
fun SsTopBarPreview() {
    SudokuSolverTheme {
        Surface {
            SsTopBar(title = "this is Title") {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "refresh")
                }
            }
        }
    }
}