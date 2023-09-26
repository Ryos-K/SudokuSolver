package com.ry05k2ulv.sudokusolver.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ry05k2ulv.sudokusolver.solver.Cell
import com.ry05k2ulv.sudokusolver.solver.Position
import com.ry05k2ulv.sudokusolver.solver.SudokuSolver
import com.ry05k2ulv.sudokusolver.solver.MutableStateSudokuTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SsViewModel @Inject constructor(
    private val sudokuSolver: SudokuSolver
) : ViewModel() {
    val table = MutableStateSudokuTable()

    var runnable = mutableStateOf(true)

    fun updateTable(value: Cell, position: Position) {
        if (!runnable.value) return
        table[position] = value
    }

    fun resetTable() {
        if (!runnable.value) return
        table.clear()
    }

    fun solve() {
        if (!runnable.value) return
        runnable.value = false
        viewModelScope.launch {
            sudokuSolver.solve(table)
            runnable.value = true
        }
    }
}