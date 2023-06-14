package cleaner.io

import cleaner.App
import cleaner.RobotExecutionPlan
import cleaner.domain.*
import java.util.regex.Pattern

class InputParserException(message: String): RuntimeException(message)

class InputParser {

    companion object {

        private fun parseInitialPosition(initialPosition: String): Position = run {
            val initialPositionMatcher = initialPositionPattern.matcher(initialPosition)
            if (!initialPositionMatcher.matches()) {
                throw InputParserException("Error input format. Parsing initialPosition: ${initialPosition}")
            }
            Position(
                coords = Coords(
                    x = initialPositionMatcher.group("initialX").toInt(),
                    y = initialPositionMatcher.group("initialY").toInt(),
                ),
                heading = Heading.parse(initialPositionMatcher.group("heading"))
            )
        }

        private fun parseCommandSequence(commandSequence: String): RobotCommandSequence = run {
            val commandSequenceMatcher = commandSequencePattern.matcher(commandSequence)
            if (!commandSequenceMatcher.matches()) {
                throw InputParserException("Error input format. Parsing commandSequence: ${commandSequence}")
            }
            RobotCommandSequence.parse(commandSequenceMatcher.group())
        }

        private fun parseTerrain(terrain: String): Terrain = run {
            val terrainMatcher = terrainPattern.matcher(terrain)
            if (!terrainMatcher.matches()) {
                throw InputParserException("Error input format. Parsing terrain: ${terrain}")
            }
            Terrain(
                maxX = Integer.parseInt(terrainMatcher.group("maxX")),
                maxY = Integer.parseInt(terrainMatcher.group("maxY"))
            )
        }

        fun parse(input: String): App = run {
            val lines = input.lines()
            if (lines.size % 2 == 0 || lines.size < 3) throw InputParserException("Error input format")

            val terrain = parseTerrain(lines[0])
            val robotExecutionPlans = lines
                .drop(1)
                .chunked(2)
                .map { robotLines ->
                    RobotExecutionPlan(
                        robot = Robot(
                            terrain = terrain,
                            position = parseInitialPosition(robotLines[0])
                        ),
                        commandSequence = parseCommandSequence(robotLines[1])
                    )
                }

            App(
                terrain = terrain,
                robotExecutionPlans = robotExecutionPlans
            )
        }

        private val terrainPattern = Pattern.compile("^(?<maxX>\\d+)\\s+(?<maxY>\\d+)$")
        private val initialPositionPattern =
            Pattern.compile("^(?<initialX>\\d+)\\s+(?<initialY>\\d+)\\s+(?<heading>[NSEW])\\s*$")
        private val commandSequencePattern = Pattern.compile("^([MLR]+)\$")
    }
}