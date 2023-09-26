package com.ry05k2ulv.sudokusolver.solver

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SudokuSolver @Inject constructor() {
    suspend fun solve(result: MutableStateSudokuTable): SudokuResult {
        return _solve(result, Position(0, 0))
    }

    private suspend fun _solve(
        result: MutableStateSudokuTable, now: Position?
    ): SudokuResult {
        if (now == null) return SudokuResult.SUCCESS
        val candidates = getCandidates(result, now) ?: return _solve(result, now.next())

        candidates.forEach { candidate ->
            result[now] = Cell.Number(candidate)
            if (_solve(result, now.next()) == SudokuResult.SUCCESS)
                return SudokuResult.SUCCESS
        }
        delay(2)
        result[now] = Cell.Empty
        return SudokuResult.FAILURE
    }

    private fun getCandidates(table: MutableStateSudokuTable, now: Position): Set<Int>? {
        if (table[now] is Cell.Number) return null
        val candidates = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        // check col
        repeat(9) { ri ->
            table[now.x, ri].getValue()?.let { candidates.remove(it) }
        }
        // check row
        repeat(9) { ci ->
            table[ci, now.y].getValue()?.let { candidates.remove(it) }
        }
        // check block
        val topLeft = calcBlockTopLeft(now)
        repeat(3) { ci ->
            repeat(3) { ri ->
                table[topLeft.x + ci, topLeft.y + ri].getValue()?.let { num -> candidates.remove(num) }
            }
        }
        return candidates
    }

    private fun calcBlockTopLeft(now: Position) = Position((now.x / 3) * 3, (now.y / 3) * 3)

    enum class SudokuResult {
        SUCCESS, FAILURE,
    }
}