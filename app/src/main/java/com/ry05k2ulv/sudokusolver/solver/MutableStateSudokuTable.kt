package com.ry05k2ulv.sudokusolver.solver

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

class MutableStateSudokuTable {
    private val table: SnapshotStateList<Cell> = List(81) { Cell.Empty }.toMutableStateList()

    operator fun get(i: Int) = table[i]

    operator fun get(x: Int, y: Int) = table[x + y * 9]
    operator fun set(x: Int, y: Int, value: Cell) {
        table[x + y * 9] = value
    }

    operator fun get(pos: Position) = table[pos.x + pos.y * 9]
    operator fun set(pos: Position, value: Cell) {
        table[pos.x + pos.y * 9] = value
    }

    fun clear() {
        for (i in 0 until 81) table[i] = Cell.Empty
    }
}

sealed class Cell {
    data class Number(val value: Int) : Cell() {
        override fun toString(): String {
            return value.toString()
        }
    }

    object Empty : Cell() {
        override fun toString(): String {
            return ""
        }
    }

    fun getValue() = when (this) {
        Empty -> null
        is Number -> value
    }
}