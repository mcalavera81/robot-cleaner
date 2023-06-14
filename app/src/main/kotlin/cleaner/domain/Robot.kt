package cleaner.domain

import java.lang.RuntimeException

class RobotException(message: String): RuntimeException(message)

class Robot(private val terrain: Terrain, var position: Position) {
    init {
        if (terrain.isOffLimits(position.coords)) {
            throw RobotException("Initial position $position not valid!")
        }
    }

    fun hasInvalidState() =
        this.terrain.isOffLimits(this.position.coords);


    fun move(): Boolean {
        if (this.hasInvalidState()) return false;
        this.position = this.position.withForwardMove();
        return true;
    }

    fun left(): Boolean {
        if (this.hasInvalidState()) return false;
        this.position = this.position.withLeftTurn();
        return true;
    }

    fun right(): Boolean {
        if (this.hasInvalidState()) return false;
        this.position = this.position.withRightTurn();
        return true;
    }

    fun handleInputCommands(robotCommandSequence: RobotCommandSequence):Unit{
        for (command in robotCommandSequence.commands) {
            command(this)
        }
    }

    override fun toString(): String {
        return "(${position.coords.x}, ${position.coords.y} ${position.heading.name})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Robot

        if (terrain != other.terrain) return false
        return position == other.position
    }

    override fun hashCode(): Int {
        var result = terrain.hashCode()
        result = 31 * result + position.hashCode()
        return result
    }

}
