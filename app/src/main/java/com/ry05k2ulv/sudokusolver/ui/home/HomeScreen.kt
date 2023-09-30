package com.ry05k2ulv.sudokusolver.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ry05k2ulv.sudokusolver.solver.Cell
import com.ry05k2ulv.sudokusolver.solver.Position
import com.ry05k2ulv.sudokusolver.ui.components.KeyType
import com.ry05k2ulv.sudokusolver.ui.components.WideKeypad
import com.ry05k2ulv.sudokusolver.ui.components.SsTopBar
import com.ry05k2ulv.sudokusolver.ui.components.Sudoku
import com.ry05k2ulv.sudokusolver.ui.theme.SudokuSolverTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onSettingsClick: () -> Unit,

) {
    var selected by remember { mutableStateOf(Position(0, 0)) }
    val table = viewModel.table
    val runnable = viewModel.runnable.value
    Column {
        SsTopBar(title = "Solver") {
            val iconColors =
                IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
            when (runnable) {
                true -> RunButton(onClick = { viewModel.solve() }, colors = iconColors)
                false -> StopButton(onClick = { viewModel.cancelSolver() }, colors = iconColors)
            }
            ResetButton(onClick = { viewModel.resetTable() }, colors = iconColors, enabled = runnable)
            SettingsButton(onClick = onSettingsClick, colors = iconColors, enabled = runnable)
        }

        Sudoku(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            table = table,
            selected = selected,
            onSelected = { selected = it },
            showHighlight = runnable
        )

        WideKeypad(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .padding(6.dp, 8.dp),
            onClick = { keyType ->
                when (keyType) {
                    is KeyType.Number -> {
                        viewModel.updateTable(Cell.Number(keyType.n), selected)
                        selected = selected.next() ?: Position(8, 8)
                    }

                    is KeyType.Delete -> {
                        selected = selected.prev() ?: Position(0, 0)
                        viewModel.updateTable(Cell.Empty, selected)
                    }

                    is KeyType.Next -> selected = selected.next() ?: Position(8, 8)
                }
            },
            enabled = runnable
        )
    }
}

@Composable
private fun RunButton(
    onClick: () -> Unit,
    colors: IconButtonColors
) {
    IconButton(onClick = onClick, colors = colors) {
        Icon(
            imageVector = Icons.Filled.ArrowRight,
            contentDescription = "solver run",
            Modifier.size(48.dp)
        )
    }
}

@Composable
private fun StopButton(
    onClick: () -> Unit,
    colors: IconButtonColors
) {
    IconButton(onClick = onClick, colors = colors) {
        Icon(
            imageVector = Icons.Filled.Stop,
            contentDescription = "solver stop",
            Modifier.size(42.dp)
        )
    }
}

@Composable
private fun ResetButton(
    onClick: () -> Unit,
    colors: IconButtonColors,
    enabled: Boolean
) {
    IconButton(
        onClick = onClick,
        colors = colors,
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = "refresh",
            Modifier.size(32.dp)
        )
    }
}

@Composable
private fun SettingsButton(
    onClick: () -> Unit,
    colors: IconButtonColors,
    enabled: Boolean
) {
    IconButton(
        onClick = onClick,
        colors = colors,
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "settings",
            Modifier.size(32.dp)
        )
    }
}

@Preview
@Composable
fun InputScreenPreview() {
    SudokuSolverTheme {
        Surface {
            HomeScreen(onSettingsClick = {})
        }
    }
}