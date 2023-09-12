package com.ry05k2ulv.sudokusolver.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ry05k2ulv.sudokusolver.solver.SudokuSolver
import com.ry05k2ulv.sudokusolver.ui.components.Sudoku
import com.ry05k2ulv.sudokusolver.ui.input.InputScreen

@Composable
fun SsApp() {
    val ssViewModel = SsViewModel(SudokuSolver())
    InputScreen(ssViewModel)
}
