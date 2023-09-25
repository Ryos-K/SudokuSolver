package com.ry05k2ulv.sudokusolver.solver

import com.ry05k2ulv.sudokusolver.ui.components.Position
import java.util.Stack

class SudokuSolver {
    fun solve(table: Array<Array<Int?>>): Array<Array<Int?>>? {
        val result = table.clone()
        return when(_solve(result, table, Position(0, 0))) {
            SudokuResult.SUCCESS -> result
            SudokuResult.FAILURE -> null
        }
    }

    private fun _solve(
        result: Array<Array<Int?>>, table: Array<Array<Int?>>, now: Position?
    ): SudokuResult {
        if (now == null) return SudokuResult.SUCCESS
        val candidates = getCandidates(result, now) ?: return _solve(result, table, now.next())
        candidates.forEach { candidate ->
            result[now.x][now.y] = candidate
            if (_solve(result, table, now.next()) == SudokuResult.SUCCESS)
                return SudokuResult.SUCCESS
        }
        result[now.x][now.y] = null
        return SudokuResult.FAILURE
    }

    private fun getCandidates(table: Array<Array<Int?>>, now: Position): Set<Int>? {
        if (table[now.x][now.y] != null) return null
        val candidates = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        // check col
        repeat(9) { ri ->
            table[now.x][ri]?.let { num -> candidates.remove(num) }
        }
        // check row
        repeat(9) { ci ->
            table[ci][now.y]?.let { num -> candidates.remove(num) }
        }
        // check block
        val topLeft = calcBlockTopLeft(now)
        repeat(3) { ci ->
            repeat(3) { ri ->
                table[topLeft.x + ci][topLeft.y + ri]?.let { num -> candidates.remove(num) }
            }
        }
        return candidates
    }

    private fun calcBlockTopLeft(now: Position) = Position((now.x / 3) * 3, (now.y / 3) * 3)

    private enum class SudokuResult {
        SUCCESS, FAILURE,
    }
}