package com.ry05k2ulv.sudokusolver.ui.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.ry05k2ulv.sudokusolver.solver.Cell
import com.ry05k2ulv.sudokusolver.solver.Position
import com.ry05k2ulv.sudokusolver.ui.SsViewModel
import com.ry05k2ulv.sudokusolver.ui.components.KeyType
import com.ry05k2ulv.sudokusolver.ui.components.Keypad
import com.ry05k2ulv.sudokusolver.ui.components.SsTopBar
import com.ry05k2ulv.sudokusolver.ui.components.Sudoku
import com.ry05k2ulv.sudokusolver.ui.theme.SudokuSolverTheme

@Composable
fun InputScreen(
    ssViewModel: SsViewModel
) {
    var selected by remember { mutableStateOf(Position(0, 0)) }
    val table = ssViewModel.table
    Column {
        SsTopBar(title = "Sudoku Solver") {
            val iconColor =
                IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
            IconButton(onClick = { ssViewModel.solve() }, colors = iconColor) {
                Icon(
                    imageVector = Icons.Filled.ArrowRight,
                    contentDescription = "solver run",
                    Modifier.size(48.dp)
                )
            }
            IconButton(onClick = { ssViewModel.resetTable() }, colors = iconColor) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "refresh",
                    Modifier.size(32.dp)
                )
            }
        }

        Sudoku(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(16.dp),
            table = table,
            selected = selected,
            onSelected = { selected = it })

        Keypad(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
            .padding(6.dp, 8.dp),
            onClick = { keyType ->
                when (keyType) {
                    is KeyType.Number -> ssViewModel.updateTable(Cell.Number(keyType.n), selected)
                    is KeyType.Delete -> ssViewModel.updateTable(Cell.Empty, selected)
                    is KeyType.Next -> selected.next()?.let { selected = it }
                }
            })
    }
}

@Preview
@Composable
fun InputScreenPreview() {
    SudokuSolverTheme {
        Surface {
            val ssViewModel = SsViewModel()
            InputScreen(ssViewModel)
        }
    }
}