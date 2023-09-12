package com.ry05k2ulv.sudokusolver.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ry05k2ulv.sudokusolver.solver.SudokuSolver
import com.ry05k2ulv.sudokusolver.ui.components.Position

class SsViewModel(
    sudokuSolver: SudokuSolver = SudokuSolver()
) : ViewModel() {
    private var _table = mutableStateOf(Array(9) { Array<Int?>(9) { null } })
    val table
        get() = _table



    fun updateTable(value: Int?, position: Position) {
        _table.value = _table.value.clone().apply { this[position.x][position.y] = value }
    }
    fun resetTable() {
        _table.value = Array(9) { Array<Int?>(9) { null } }
    }

    fun solve() {

    }
}