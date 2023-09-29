package com.ry05k2ulv.sudokusolver.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stop
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.ry05k2ulv.sudokusolver.solver.Cell
import com.ry05k2ulv.sudokusolver.solver.Position
import com.ry05k2ulv.sudokusolver.ui.components.KeyType
import com.ry05k2ulv.sudokusolver.ui.components.Keypad
import com.ry05k2ulv.sudokusolver.ui.components.SsTopBar
import com.ry05k2ulv.sudokusolver.ui.components.Sudoku
import com.ry05k2ulv.sudokusolver.ui.theme.SudokuSolverTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var selected by remember { mutableStateOf(Position(0, 0)) }
    val table = homeViewModel.table
    val runnable = homeViewModel.runnable.value
    Column {
        SsTopBar(title = "Sudoku Solver") {
            val iconColor =
                IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
            when (runnable) {
                true -> {
                    IconButton(onClick = { homeViewModel.solve() }, colors = iconColor) {
                        Icon(
                            imageVector = Icons.Filled.ArrowRight,
                            contentDescription = "solver run",
                            Modifier.size(48.dp)
                        )
                    }
                }
                false -> {
                    IconButton(onClick = { homeViewModel.cancelSolver() }, colors = iconColor) {
                        Icon(
                            imageVector = Icons.Filled.Stop,
                            contentDescription = "solver stop",
                            Modifier.size(42.dp)
                        )
                    }
                }
            }

            IconButton(onClick = { homeViewModel.resetTable() }, colors = iconColor, enabled = runnable) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "refresh",
                    Modifier.size(32.dp)
                )
            }
            
            IconButton(onClick = {  }) {
                
            }
        }

        Sudoku(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(16.dp),
            table = table,
            selected = selected,
            onSelected = { selected = it },
            showHighlight = runnable)

        Keypad(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
            .padding(6.dp, 8.dp),
            onClick = { keyType ->
                when (keyType) {
                    is KeyType.Number -> {
                        homeViewModel.updateTable(Cell.Number(keyType.n), selected)
                        selected = selected.next() ?: Position(8, 8)
                    }
                    is KeyType.Delete -> {
                        selected = selected.prev() ?: Position(0, 0)
                        homeViewModel.updateTable(Cell.Empty, selected)
                    }
                    is KeyType.Next -> selected = selected.next() ?: Position(8, 8)
                }
            },
            enabled = runnable
        )
    }
}

@Preview
@Composable
fun InputScreenPreview() {
    SudokuSolverTheme {
        Surface {
            HomeScreen()
        }
    }
}