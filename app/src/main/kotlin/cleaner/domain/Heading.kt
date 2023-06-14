package cleaner.domain

import java.lang.RuntimeException

enum class Heading(val id: String) {
    NORTH("N") {
        override fun rightTurn(): Heading = EAST
        override fun leftTurn() : Heading = WEST
        override fun forwardStep(): Coords = Coords(0,+1)
    },
    SOUTH("S") {
        override fun rightTurn(): Heading = WEST
        override fun leftTurn() : Heading = EAST
        override fun forwardStep(): Coords = Coords(0,-1)
    },
    WEST("W") {
        override fun rightTurn(): Heading = NORTH
        override fun leftTurn() : Heading = SOUTH
        override fun forwardStep(): Coords = Coords(-1,0)
    },
    EAST("E") {
        override fun rightTurn(): Heading = SOUTH
        override fun leftTurn() : Heading = NORTH
        override fun forwardStep(): Coords = Coords(+1,0)
    };

    abstract fun rightTurn():Heading
    abstract fun leftTurn():Heading
    abstract fun forwardStep():Coords

    companion object{
        fun parse( initialChar: String) = when(initialChar){
            "N" -> NORTH
            "S" -> SOUTH
            "E" -> EAST
            "W" -> WEST
            else -> throw RuntimeException("Error parsing heading. $initialChar")
        }
    }

}
