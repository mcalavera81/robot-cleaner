package cleaner.io

import cleaner.domain.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class InputParserTest {

    @Test
    fun `parse a sample file successfully with a single robot execution plan`() {
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
        """.trimIndent()
        val robotApp = InputParser.parse(input)
        val terrain = Terrain(5, 5)
        assertEquals(terrain, robotApp.terrain)

        assertEquals(
            robotApp.robotExecutionPlans.first().robot,
            Robot(terrain = terrain, position = Position(coords = Coords(x = 1, y = 2), heading = Heading.NORTH))
        )
        assertEquals(
            robotApp.robotExecutionPlans.first().commandSequence,
            RobotCommandSequence(
                listOf(
                    turnLeftCommand,
                    moveForwardCommand,
                    turnLeftCommand,
                    moveForwardCommand,
                    turnLeftCommand,
                    moveForwardCommand,
                    turnLeftCommand,
                    moveForwardCommand,
                    moveForwardCommand
                )
            )
        )
    }

    @Test
    fun `parse a sample file successfully with 2 robot execution plans`() {
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()
        val robotApp = InputParser.parse(input)
        val terrain = Terrain(5, 5)
        assertEquals(terrain, robotApp.terrain)

        assertEquals(
            robotApp.robotExecutionPlans.first().robot,
            Robot(terrain = terrain, position = Position(coords = Coords(x = 1, y = 2), heading = Heading.NORTH))
        )
        assertEquals(
            robotApp.robotExecutionPlans.first().commandSequence,
            RobotCommandSequence(
                listOf(
                    turnLeftCommand,
                    moveForwardCommand,
                    turnLeftCommand,
                    moveForwardCommand,
                    turnLeftCommand,
                    moveForwardCommand,
                    turnLeftCommand,
                    moveForwardCommand,
                    moveForwardCommand
                )
            )
        )

        assertEquals(
            robotApp.robotExecutionPlans.drop(1).first().robot,
            Robot(terrain = terrain, position = Position(coords = Coords(x = 3, y = 3), heading = Heading.EAST))
        )

        assertEquals(
            robotApp.robotExecutionPlans.drop(1).first().commandSequence,
            RobotCommandSequence(
                listOf(
                    moveForwardCommand,
                    moveForwardCommand,
                    turnRightCommand,
                    moveForwardCommand,
                    moveForwardCommand,
                    turnRightCommand,
                    moveForwardCommand,
                    turnRightCommand,
                    turnRightCommand,
                    moveForwardCommand
                )
            )
        )
    }

    @Test
    fun `error parsing a sample file - No execution plans`() {
        val input = """
            5 5
        """.trimIndent()

        assertThrows<InputParserException> {
            InputParser.parse(input)
        }

    }

    @Test
    fun `error parsing a sample file - Terrain wrong format`() {
        val input = """
            55
            1 2 N
            LMLMLMLMM
        """.trimIndent()

        assertThrows<InputParserException> {
            InputParser.parse(input)
        }
    }

    @Test
    fun `error parsing a sample file - robot execution plan wrong format - sample 1`() {
        val input = """
            5 5
            12 N
            LMLMLMLMM
        """.trimIndent()

        assertThrows<InputParserException> {
            InputParser.parse(input)
        }
    }

    @Test
    fun `error parsing a sample file - robot execution plan wrong format - sample 2`() {
        val input = """
            5 5
            1 2 N
            ABC
        """.trimIndent()

        assertThrows<InputParserException> {
            InputParser.parse(input)
        }
    }

    @Test
    fun `error parsing a sample file - robot execution plan wrong format - sample 3`() {
        val input = """
            5 5
            1 2 N
        """.trimIndent()

        assertThrows<InputParserException> {
            InputParser.parse(input)
        }
    }

    @Test
    fun `error parsing a sample file - robot execution plan wrong format - sample 4`() {
        val input = """
            5 5
            LMLMLMLMM
        """.trimIndent()

        assertThrows<InputParserException> {
            InputParser.parse(input)
        }
    }


}