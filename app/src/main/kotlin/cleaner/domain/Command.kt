package cleaner.domain

import java.lang.RuntimeException

class Command(private val command: (robot: Robot) -> Unit) {
    operator fun invoke(robot: Robot) = command(robot);
}

val moveForwardCommand = Command(Robot::move)
val turnRightCommand = Command(Robot::right)
val turnLeftCommand = Command(Robot::left)


class CommandSequenceException(message: String): RuntimeException(message)

data class RobotCommandSequence(val commands: List<Command>){
    companion object {
        fun parse(commands: String): RobotCommandSequence= run {
            RobotCommandSequence(
                commands.fold(listOf<Command>()){
                        acc:List<Command>, c:Char ->
                    when(c){
                        'M' -> acc + moveForwardCommand
                        'R' -> acc + turnRightCommand
                        'L' -> acc + turnLeftCommand
                        else -> throw CommandSequenceException("Unrecognized Command $c")
                    }
                }
            )
        }
    }
}
