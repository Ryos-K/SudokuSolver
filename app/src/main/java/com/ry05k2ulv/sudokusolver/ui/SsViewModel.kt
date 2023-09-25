package com.ry05k2ulv.sudokusolver.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ry05k2ulv.sudokusolver.solver.Cell
import com.ry05k2ulv.sudokusolver.solver.Position
import com.ry05k2ulv.sudokusolver.solver.SudokuSolver
import com.ry05k2ulv.sudokusolver.solver.MutableStateSudokuTable
import kotlinx.coroutines.launch

class SsViewModel(
    val sudokuSolver: SudokuSolver = SudokuSolver()
) : ViewModel() {
    val table = MutableStateSudokuTable()

    fun updateTable(value: Cell, position: Position) {
        table[position] = value
    }

    fun resetTable() {
        table.clear()
    }

    fun solve() {
        viewModelScope.launch {
            sudokuSolver.solve(table)
        }
    }
}