package app.fabianomello.materialcalculator.domain

enum class Operation(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    PERCENT('%')
}

val operationSymbols = Operation.values()
    .map { it.symbol }
    .joinToString("")

fun operationFromSymbol(symbol: Char): Operation =
    Operation.values().find { it.symbol == symbol }
        ?: throw IllegalArgumentException("Invalid symbol")
