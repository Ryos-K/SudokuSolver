package com.ry05k2ulv.sudokusolver.solver

data class Position(val x: Int, val y: Int) {
    fun next() = when {
        x == 8 && y == 8 -> null
        x == 8 -> Position(0, y + 1)
        else -> Position(x + 1, y)
    }

    fun prev() = when {
        x == 0 && y == 0 -> null
        x == 0 -> Position(8, y - 1)
        else -> Position(x - 1, y)
    }
}
