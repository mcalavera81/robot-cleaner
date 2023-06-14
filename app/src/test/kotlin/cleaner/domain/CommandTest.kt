package cleaner.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class CommandTest {

    @ParameterizedTest
    @MethodSource("commandSequence")
    fun `should be able to parse valid commands`(commandsStr: String, commands: List<Command>){

        val commandSequence = RobotCommandSequence.parse(commandsStr)
        assertEquals(commands,commandSequence.commands)

    }

    @Test
    fun `should throw exceptions when entering not valid commands`(){
        assertThrows<CommandSequenceException> {
            RobotCommandSequence.parse("ABC")
        }
    }

    companion object {
        @JvmStatic
        fun commandSequence() = listOf(
            Arguments.of("LMLMLMLMM",listOf(
                turnLeftCommand,
                moveForwardCommand,
                turnLeftCommand,
                moveForwardCommand,
                turnLeftCommand,
                moveForwardCommand,
                turnLeftCommand,
                moveForwardCommand,
                moveForwardCommand
            )),
            Arguments.of("MMRMMRMRRM",listOf(
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
            ))
        )
    }
}