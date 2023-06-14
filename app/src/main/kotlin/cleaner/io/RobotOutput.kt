package cleaner.io

import cleaner.domain.Heading
import cleaner.domain.Robot

class RobotOutput {
    companion object {
        fun printRobot(robot: Robot): String =
            "${robot.position.coords.x} ${robot.position.coords.y} ${robot.position.heading.id}"

    }
}