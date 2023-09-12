package com.ry05k2ulv.sudokusolver.solver

import com.ry05k2ulv.sudokusolver.ui.components.Position
import java.util.Stack

class SudokuSolver {
    fun solve(table: Array<Array<Int?>>): Array<Array<Int>>? {
        val result = table.clone()
        _solve(result, table)
        return null
    }

    private fun _solve(result: Array<Array<Int?>>, table: Array<Array<Int?>>) {
        val candidatesStack = Stack<Set<Int>?>()
        var now: Position? = Position(0, 0)
        while(now != null) {
            candidatesStack.push(getCandidates(result, now))
            candidatesStack.peek()
            now = now.next()
        }
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
}