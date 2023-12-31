package com.ry05k2ulv.sudokusolver.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ry05k2ulv.sudokusolver.solver.Cell
import com.ry05k2ulv.sudokusolver.solver.MutableStateSudokuTable
import com.ry05k2ulv.sudokusolver.solver.Position
import com.ry05k2ulv.sudokusolver.solver.SudokuSolver
import com.ry05k2ulv.sudokusolver.solver.SudokuTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sudokuSolver: SudokuSolver
) : ViewModel() {
    private val _table = MutableStateSudokuTable()
    val table: SudokuTable
        get() = _table

    var job: Job? = null
    var runnable = mutableStateOf(true)

    fun updateTable(value: Cell, position: Position) {
        if (!runnable.value) return
        _table[position] = value
    }

    fun clearTable() {
        if (!runnable.value) return
        _table.clear()
    }

    fun runSolver() {
        if (!runnable.value) return
        runnable.value = false
        job = viewModelScope.launch {
            sudokuSolver.solve(_table)
            runnable.value = true
        }
    }

    fun stopSolver() {
        if (!runnable.value) {
            job?.cancel()
            runnable.value = true
        }
    }
}