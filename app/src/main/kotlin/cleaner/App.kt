package cleaner

import cleaner.domain.RobotCommandSequence
import cleaner.domain.Robot
import cleaner.domain.Terrain
import cleaner.io.InputParser
import cleaner.io.RobotOutput
import java.io.File

data class App(val terrain: Terrain, val robotExecutionPlans: List<RobotExecutionPlan>)
data class RobotExecutionPlan(val robot: Robot, val commandSequence: RobotCommandSequence)

fun main(args: Array<String>) {

    if(args.size != 1){
        println("Usage: <exec> <filepath>")
    }
    val input = File(args[0]).readText()
    val app = InputParser.parse(input)

    val output = app.robotExecutionPlans.map {
        it.robot.handleInputCommands(it.commandSequence)
        RobotOutput.printRobot(it.robot)
    }

    output.forEach(::println)


}
