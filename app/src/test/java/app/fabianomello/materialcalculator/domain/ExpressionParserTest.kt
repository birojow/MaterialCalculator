package app.fabianomello.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `simple expression is properly parsed`() {
        parser = ExpressionParser("3+5-3*4/3")

        val parts = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        assertThat(parts).isEqualTo(expected)
    }

    @Test
    fun `expression with parentheses is properly parsed`() {
        parser = ExpressionParser("4-(4*5)")

        val parts = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing)
        )
        assertThat(parts).isEqualTo(expected)
    }

    @Test
    fun `complex expression is properly parsed`() {
        val parser = ExpressionParser("3+(25/(4.5*9)-32.85*7)/3.14")

        val parts = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(25.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.5),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(9.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(32.85),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(7.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.14),
        )
        assertThat(parts).isEqualTo(expected)
    }
}
