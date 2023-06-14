package cleaner.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals


class RobotTest {

    @Test
    fun `should throw an exception when initial position is off limits (11,10)`() {
        val terrain = Terrain(10, 10)
        val exception = assertThrows<RobotException> {
            Robot(terrain, Position(Coords(11, 10), Heading.NORTH))
        }
        assertEquals("Initial position Position(coords=Coords(x=11, y=10), heading=NORTH) not valid!", exception.message)
    }

    @Test
    fun `When command sequence is valid, should handle commands successfully, Sample 1`() {
        val terrain = Terrain(maxX = 5, maxY = 5);
        val robot = Robot(terrain, Position(Coords(x = 1, y = 2), Heading.NORTH));

        robot.handleInputCommands(
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
        assertEquals(false, robot.hasInvalidState())
        assertEquals(Coords(x = 1, y = 3), robot.position.coords)
        assertEquals(Heading.NORTH, robot.position.heading)
    }

    @Test
    fun `When command sequence is valid, should handle commands successfully, Sample 2`() {
        val terrain = Terrain(maxX = 5, maxY = 5);
        val robot = Robot(terrain, Position(Coords(x = 3, y = 3), Heading.EAST));

        robot.handleInputCommands(
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
        assertEquals(false, robot.hasInvalidState())
        assertEquals(Coords(x = 5, y = 1), robot.position.coords)
        assertEquals(Heading.EAST, robot.position.heading)
    }

    @Test
    fun `When command sequence is not valid, should end up with the robot in an invalid state`(){
        val terrain = Terrain(maxX = 5, maxY = 5);
        val robot = Robot(terrain, Position(Coords(4, 4), Heading.EAST))

        robot.handleInputCommands(
            RobotCommandSequence(
                listOf(
                    moveForwardCommand,
                    moveForwardCommand,
                    moveForwardCommand
                )
            )
        )
        assertEquals(true, robot.hasInvalidState())
    }
}